package jukebox;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayList {

    private Queue<Song> songQueue;

    public PlayList() {
        songQueue = new LinkedList<>();
    }

    public void queueUpSong(Song song) {
        songQueue.add(song);
    }

    public Song nextSong() {
        return songQueue.peek();
    }

    public Song removeNextSong() {
        return songQueue.remove();
    }

    public void addSongs(Set<Song> songs) {
        songs.stream().forEach(song -> songQueue.add(song));
    }

    public boolean hasMoreSongs() {
        return !songQueue.isEmpty();
    }

    public List<Song> listSongs() {
        return songQueue.stream().collect(Collectors.toList());
    }
}
