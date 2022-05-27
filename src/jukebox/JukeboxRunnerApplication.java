package jukebox;

import java.util.HashSet;
import java.util.Set;

public class JukeboxRunnerApplication {

    public static void main(String[] args) throws InterruptedException {

        User user = new User("song-lover-1");
        CDPlayer cdPlayer = new CDPlayer();
        PlayList jukeboxPlayList = new PlayList();
        for (int i = 1; i <= 10; i++) {
            jukeboxPlayList.queueUpSong(new Song("juke-song-" + i, i));
        }

        Set<Song> rockSongs = new HashSet<>();
        for (int i = 1; i <= 5; i++) {
            rockSongs.add(new Song("cd-rock-song-" + i, i));
        }
        CD rockCD = new CD("cd-rock-1", rockSongs);

        Set<Song> jazzSongs = new HashSet<>();
        for (int i = 1; i <= 5; i++) {
            jazzSongs.add(new Song("cd-jazz-song-" + i, i));
        }
        CD jazzCD = new CD("cd-jazz-1", jazzSongs);

        Jukebox jukebox = new Jukebox(user, cdPlayer, jukeboxPlayList);
        jukebox.playFromJukeBoxPlayList();

        // Wait and then play from CD
        Thread.sleep(3000);
        jukebox.playFromCD(jazzCD);

        // Wait and then play from another CD
        Thread.sleep(5000);
        jukebox.playFromCD(rockCD);

        // Wait and resume
        Thread.sleep(6000);
        jukebox.pause();
        System.out.println("Paused the Jukebox");
        Thread.sleep(3000);
        jukebox.resume();

        while(!jukebox.isPlaylistDone()) {
            // Do nothing
        }

        System.out.println("Finally done!!!");
        System.exit(0);
    }
}
