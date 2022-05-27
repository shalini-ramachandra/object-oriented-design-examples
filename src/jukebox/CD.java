package jukebox;

import java.util.Set;
import java.util.UUID;

public class CD {

    private final UUID id;
    private final String title;
    private final Set<Song> songs;
    private String artist;

    public CD(String title, Set<Song> songs) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.songs = songs;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
