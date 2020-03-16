package com.telerik.ridepalplaylistgenerator.service;

import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.models.Track;
import com.telerik.ridepalplaylistgenerator.repository.TrackRepository;
import com.telerik.ridepalplaylistgenerator.service.interfaces.GeneratePlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GeneratePlaylistServiceImpl implements GeneratePlaylistService {

    private TrackRepository trackRepository;

    @Autowired
    public GeneratePlaylistServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public List<Track> createPlaylistTracks(int duration, HashMap<Genre, Integer> durationPercentByGenre, boolean useSameArtist,
                                     boolean useTopTracks) {
        List<Track> tracksForPlaylist = new ArrayList<>();
        List<Track> tracksByGenre = new ArrayList<>();
        Set<Long> artists = new HashSet<>();
        Random rand = new Random();
        int remainderOfPreviousGenreDuration = 0;
        int index = 0;
        int currentDur = 0;

        for (Map.Entry<Genre, Integer> entry : durationPercentByGenre.entrySet()) {
            int timeForGenre = getTimeForGenreByPercent(duration, entry.getValue()) + remainderOfPreviousGenreDuration;
            if (useTopTracks) {
                tracksByGenre = trackRepository.getTracksByGenreAndRank(entry.getKey().getId(), timeForGenre);
            } else {
                tracksByGenre = trackRepository.getTracksByGenre(entry.getKey().getId(), timeForGenre);
            }

            while (timeForGenre > 0) {
                if(tracksByGenre.size() == 0){
                    remainderOfPreviousGenreDuration = timeForGenre;
                    break;
                }
                index = rand.nextInt(tracksByGenre.size());
                Track track = tracksByGenre.get(index);
                tracksForPlaylist.add(track);
                timeForGenre -= track.getDuration();
                currentDur += track.getDuration();
                if(!useSameArtist){
                    artists.add(track.getArtist().getId());
                }
                for(int i = 0; i < tracksByGenre.size(); i++){
                    if(tracksByGenre.get(i).getDuration() > timeForGenre || artists.contains(tracksByGenre.get(i).getArtist().getId())){
                        tracksByGenre.remove(tracksByGenre.get(i));
                    }
                }
            }
        }
        adjustPlaylistForTimeConstraints(tracksForPlaylist, duration, currentDur);
        return tracksForPlaylist;
    }

    private void adjustPlaylistForTimeConstraints(List<Track> tracksForPlaylist, int duration, int currentDur){
        Collections.shuffle(tracksForPlaylist);
        for(int i = 0; i < tracksForPlaylist.size(); i++){
            if(currentDur > duration-300 && currentDur < duration+300){
                break;
            }
            if((currentDur - tracksForPlaylist.get(i).getDuration()) > duration-300){
                currentDur -= tracksForPlaylist.get(i).getDuration();
                tracksForPlaylist.remove(i);
            }
        }
    }

    private int getTimeForGenreByPercent(int totalDuration, int percentByGenre) {
        return ((totalDuration * percentByGenre) / 100);
    }

    @Override
    public int calculatePlaylistDuration(List<Track> playlist){
        int duration = 0;
        for(Track track : playlist){
            duration += track.getDuration();
        }
        return duration;
    }

    @Override
    public int calculatePlaylistRank(List<Track> playlist) {
        long currentRank = 0;
        for(Track track : playlist){
            currentRank += track.getRank();
        }
        return (int) (currentRank/playlist.size());
    }

}