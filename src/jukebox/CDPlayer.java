package jukebox;

public class CDPlayer {

    private CD currentCD;

    private PlayList playList;

    public CD getCurrentCD() {
        return currentCD;
    }

    public void setCurrentCD(CD cd) {
        currentCD = cd;
        playList = new PlayList();
        playList.addSongs(currentCD.getSongs());
    }

    public PlayList getPlayList() {
        return playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }

}
