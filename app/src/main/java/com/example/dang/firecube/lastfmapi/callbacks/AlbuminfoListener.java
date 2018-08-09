package com.example.dang.firecube.lastfmapi.callbacks;

import com.example.dang.firecube.lastfmapi.models.LastfmAlbum;

public interface AlbuminfoListener {

    void albumInfoSucess(LastfmAlbum album);

    void albumInfoFailed();

}
