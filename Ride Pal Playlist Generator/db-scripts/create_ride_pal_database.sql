DROP SCHEMA IF EXISTS `ride_pal`;
create database if not exists ride_pal;
use ride_pal;

CREATE TABLE users
(
    id    INT(10)NOT NULL AUTO_INCREMENT,
    username   VARCHAR(60) NOT NULL UNIQUE,
    password   VARCHAR(68),
    first_name VARCHAR(60),
    last_name  VARCHAR(60),
    email      VARCHAR(50) UNIQUE,
    age        INT(10) default 0,
    enabled    BOOLEAN DEFAULT 1,
    picture longblob,
    primary key (username),
    index (id),
    check (length(username) > 2),
    check (length(first_name) > 2),
    check (length(last_name) > 2),
    check (length(email) > 6),
    check (length(password) > 6)
);

CREATE TABLE playlists
(
    playlist_id INT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(60) NOT NULL,
    description VARCHAR(200),
    duration    INT         NOT NULL,
    `rank`      INT         NOT NULL,
    user_id     INT         NOT NULL,
    cover       longblob,
    enabled     BOOLEAN DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users (id),
    check (length(title) > 2)
);

CREATE TABLE genres
(
    genre_id BIGINT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    picture  VARCHAR(100),
    enabled     BOOLEAN DEFAULT 1
);

CREATE TABLE authorities
(
    username  VARCHAR(60) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    UNIQUE KEY username_authority (username, authority),
    CONSTRAINT authorities_fk FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE artists
(
    artist_id BIGINT primary key ,
    artist_name varchar(255) not null,
    picture varchar(100) null,
    genre_id bigint default 0 not null,
    enabled tinyint(1) default 1 null
);

CREATE TABLE albums
(
    album_id BIGINT primary key ,
    title varchar(255) not null,
    cover varchar(100) null,
    artist_id bigint default 0 null,
    duration int default 0 not null,
    genre_id bigint default 0 not null,
    tracklist varchar(255) null
);

CREATE TABLE tracks
(
    track_id bigint not null primary key ,
    title varchar(255) null,
    duration int null,
    `rank` int null,
    enabled tinyint(1) default 1 null,
    preview varchar(255) null,
    genre_id bigint default 0 null,
    artist_id bigint null,
    album_id bigint null,
    constraint tracks_ibfk_1
        foreign key (artist_id) references ride_pal.artists (artist_id)
);

CREATE TABLE playlists_genres
(
    playlist_genres_id INT AUTO_INCREMENT PRIMARY KEY,
    playlist_id        INT NOT NULL,
    genre_id           bigint NOT NULL,
    FOREIGN KEY (playlist_id) REFERENCES playlists (playlist_id),
    FOREIGN KEY (genre_id) REFERENCES genres (genre_id)
);

CREATE TABLE tracks_playlists
(
    track_playlist_id INT AUTO_INCREMENT PRIMARY KEY,
    track_id          bigint NOT NULL,
    playlist_id       INT NOT NULL,
    FOREIGN KEY (track_id) REFERENCES tracks (track_id),
    FOREIGN KEY (playlist_id) REFERENCES playlists (playlist_id)
);