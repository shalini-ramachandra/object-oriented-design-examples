package jukebox;

import java.util.HashSet;
import java.util.Set;

public class JukeboxRunnerApplication {

    public static void main(String[] args) throws InterruptedException {

        User user = new User("song-lover-1");
        CDPlayer cdPlayer = new CDPlayer();

        // Test 1 -
        //      * Test alternating between different playlists.
        //      * The old playlist should stop and new playlist should start
        //      * Test pause and resume of playlist
        PlayList jukeboxPlayList = createPlaylist("juke-song-", 10);
        CD rockCD = createCD("cd-rock-song-", "cd-rock", 5);
        CD jazzCD = createCD("cd-jazz-song-", "cd-jazz", 5);
        Jukebox jukebox = new Jukebox(user, cdPlayer, jukeboxPlayList);
        testPlayDifferentPlaylists(jukebox, jazzCD, rockCD);


        // Test 2 -
        //      * User should see the next song in playlist
        //      * User should be able to select a song and add to playlist
        jukeboxPlayList = createPlaylist("juke-song-", 10);
        CD classicalCD = createCD("cd-classical-song-", "cd-classical", 5);
        jukebox = new Jukebox(user, cdPlayer, jukeboxPlayList);
        testUserSelection(jukebox, jukeboxPlayList, classicalCD);

        System.exit(0);
    }

    private static PlayList createPlaylist(String songTitlePrefix, int numSongs) {
        PlayList playList = new PlayList();

        for (int i = 1; i <= numSongs; i++) {
            playList.queueUpSong(new Song(songTitlePrefix + i, i));
        }

        return playList;
    }

    private static CD createCD(String songTitlePrefix, String cdTitle, int numSongs) {
        Set<Song> songs = new HashSet<>();
        for (int i = 1; i <= numSongs; i++) {
            songs.add(new Song(songTitlePrefix + i, i));
        }
        CD cd = new CD(cdTitle, songs);

        return cd;
    }

    private static void testPlayDifferentPlaylists(Jukebox jukebox, CD cd1, CD cd2) throws InterruptedException {

        System.out.println(new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName() + " >>> Starting....");
        System.out.println("============================================================================");

        jukebox.playFromJukeBoxPlayList();

        // Wait and then play from CD
        Thread.sleep(3000);
        jukebox.playFromCD(cd1);

        // Wait and then play from another CD
        Thread.sleep(5000);
        jukebox.playFromCD(cd2);

        // Wait and resume
        Thread.sleep(6000);
        jukebox.pause();
        System.out.println("Paused the Jukebox");
        Thread.sleep(3000);
        System.out.println("Resuming the Jukebox");
        jukebox.resume();

        while(!jukebox.isPlaylistDone()) {
            // Do nothing
        }

        System.out.println("============================================================================");
        System.out.println(new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName() + " >>> Finally done!!!");
    }

    private static void testUserSelection(Jukebox jukebox, PlayList playList, CD cd) {

        System.out.println(new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName() + " >>> Starting....");
        System.out.println("============================================================================");

        System.out.println("Current Playlist: ");
        jukebox.getCurrentPlayList().listSongs().stream().forEach(song -> System.out.print(song.getTitle() + ", "));
        System.out.println("");

        System.out.println("Next Song: ");
        System.out.println(jukebox.nextSong().getTitle());

        Song song = cd.getSongs().stream().findFirst().get();
        System.out.println("Queuing Song: " + song.getTitle());
        jukebox.getCurrentPlayList().queueUpSong(song);

        System.out.println("Playlist after queueing: ");
        jukebox.getCurrentPlayList().listSongs().stream().forEach(s -> System.out.print(s.getTitle() + ", "));
        System.out.println("");

        System.out.println("Next Song: ");
        System.out.println(jukebox.nextSong().getTitle());

        System.out.println("============================================================================");
        System.out.println(new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName() + " >>> Done!!!");
    }
}
