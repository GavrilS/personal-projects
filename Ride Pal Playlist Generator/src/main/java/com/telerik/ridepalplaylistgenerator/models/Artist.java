package com.telerik.ridepalplaylistgenerator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import static com.telerik.ridepalplaylistgenerator.constants.ValidationHelper.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @Column(name = "artist_id")
    private long id;

    @Column(name = "artist_name")
    private String name;

    @JsonProperty("genre_id")
    @Column(name = "genre_id")
    private long genreId;

    @Column(name = "picture")
    private String picture;

    @Column(name = "enabled")
    private boolean enabled;

}
