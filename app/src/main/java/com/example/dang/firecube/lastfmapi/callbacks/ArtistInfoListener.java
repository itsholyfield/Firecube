package com.example.dang.firecube.lastfmapi.callbacks;

import com.example.dang.firecube.lastfmapi.models.LastfmArtist;

public interface ArtistInfoListener {

    void artistInfoSucess(LastfmArtist artist);

    void artistInfoFailed();

}
