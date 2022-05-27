package jukebox;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Jukebox {

    private User currentUser;

    private CDPlayer cdPlayer;

    private PlayList jukeBoxPlaylist;

    private PlayList currentPlayList;

    private Song currentSong;

    private boolean playListStopped = Boolean.TRUE;

    private static final ExecutorService service = Executors.newSingleThreadExecutor();
    private Future<Song> future;

    public Jukebox(User user, CDPlayer cdPlayer, PlayList jukeBoxPlaylist) {
        this.jukeBoxPlaylist = jukeBoxPlaylist;
        this.currentPlayList = jukeBoxPlaylist;
        this.currentUser = user;
        this.cdPlayer = cdPlayer;
    }

    public void playFromCD(CD cd) {
        stop();
        cdPlayer.setCurrentCD(cd);
        currentPlayList = cdPlayer.getPlayList();
        play();
    }

    public void playFromJukeBoxPlayList() {
        stop();
        currentPlayList = jukeBoxPlaylist;
        play();
    }

    public void play() {
        playListStopped = Boolean.FALSE;
        future = service.submit(new SongPlayer());
    }

    public void stop() {
        playListStopped = Boolean.TRUE;
        currentSong = null;

        if (future != null) {
            future.cancel(Boolean.TRUE);
        }
    }

    public void pause() {
        stop();
    }

    public void resume() {
        play();
    }

    public boolean isPlaylistDone() {
        return future.isDone();
    }

    public Song seeNextSong() {
        return currentPlayList.seeNextSong();
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    public boolean isPlayListStopped() {
        return playListStopped;
    }

    public void setPlayListStopped(boolean playListStopped) {
        this.playListStopped = playListStopped;
    }

    public PlayList getCurrentPlayList() {
        return currentPlayList;
    }

    public void setCurrentPlayList(PlayList currentPlayList) {
        this.currentPlayList = currentPlayList;
    }

    class SongPlayer implements Callable<Song> {

        @Override
        public Song call() {
            while (currentPlayList != null && currentPlayList.hasMoreSongs() && !playListStopped) {
                currentSong = currentPlayList.getNextSong();
                System.out.println("Playing song : " + currentSong.getTitle() + " for the next " + currentSong.getDurationInSeconds() + " seconds!");

                try {
                    Thread.sleep(currentSong.getDurationInSeconds() * 1000);
                } catch (InterruptedException e) {
                    System.err.println("Error while playing song : " + currentSong.getTitle());
                }
            }

            System.out.println("Stopped at song : " + currentSong.getTitle());
            return currentSong;
        }
    }
}
