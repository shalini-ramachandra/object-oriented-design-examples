package jukebox;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PlayList {

    private Queue<Song> songQueue;

    public PlayList() {
        songQueue = new LinkedList<>();
    }

    public void queueUpSong(Song song) {
        songQueue.add(song);
    }

    public Song seeNextSong() {
        return songQueue.peek();
    }

    public Song getNextSong() {
        return songQueue.remove();
    }

    public void addSongs(Set<Song> songs) {
        songs.stream().forEach(song -> songQueue.add(song));
    }

    public boolean hasMoreSongs() {
        return !songQueue.isEmpty();
    }


}
