package jukebox;

import java.util.UUID;

public class Song {

    private final UUID id;
    private final String title;
    private String artist;
    private String album;
    private String genre;
    private int durationInSeconds;

    public Song(String title, int duration) {
        this.id = UUID.randomUUID();
        this.title = title;
        durationInSeconds = duration;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }
}
