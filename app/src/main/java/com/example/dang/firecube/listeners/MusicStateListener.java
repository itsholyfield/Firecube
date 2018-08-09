package com.example.dang.firecube.listeners;

/**
 * Listens for playback changes to send the the fragments bound to this activity
 */
public interface MusicStateListener {

    /**
     * Called when {@link com.example.dang.firecube.MusicService#REFRESH} is invoked
     */
    void restartLoader();

    /**
     * Called when {@link com.example.dang.firecube.MusicService#PLAYLIST_CHANGED} is invoked
     */
    void onPlaylistChanged();

    /**
     * Called when {@link com.example.dang.firecube.MusicService#META_CHANGED} is invoked
     */
    void onMetaChanged();

}
