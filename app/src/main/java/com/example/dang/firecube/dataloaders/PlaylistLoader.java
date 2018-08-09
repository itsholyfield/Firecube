package com.example.dang.firecube.dataloaders;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.PlaylistsColumns;

import com.example.dang.firecube.models.Playlist;
import com.example.dang.firecube.utils.FirecubeUtils;

import java.util.ArrayList;
import java.util.List;

public class PlaylistLoader {

    static ArrayList<Playlist> mPlaylistList;
    private static Cursor mCursor;

    public static List<Playlist> getPlaylists(Context context, boolean defaultIncluded) {

        mPlaylistList = new ArrayList<>();

        if (defaultIncluded)
            makeDefaultPlaylists(context);

        mCursor = makePlaylistCursor(context);

        if (mCursor != null && mCursor.moveToFirst()) {
            do {

                final long id = mCursor.getLong(0);

                final String name = mCursor.getString(1);

                final int songCount = FirecubeUtils.getSongCountForPlaylist(context, id);

                final Playlist playlist = new Playlist(id, name, songCount);

                mPlaylistList.add(playlist);
            } while (mCursor.moveToNext());
        }
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
        return mPlaylistList;
    }

    private static void makeDefaultPlaylists(Context context) {
        final Resources resources = context.getResources();

        /* Last added list */
        final Playlist lastAdded = new Playlist(FirecubeUtils.PlaylistType.LastAdded.mId,
                resources.getString(FirecubeUtils.PlaylistType.LastAdded.mTitleId), -1);
        mPlaylistList.add(lastAdded);

        /* Recently Played */
        final Playlist recentlyPlayed = new Playlist(FirecubeUtils.PlaylistType.RecentlyPlayed.mId,
                resources.getString(FirecubeUtils.PlaylistType.RecentlyPlayed.mTitleId), -1);
        mPlaylistList.add(recentlyPlayed);

        /* Top Tracks */
        final Playlist topTracks = new Playlist(FirecubeUtils.PlaylistType.TopTracks.mId,
                resources.getString(FirecubeUtils.PlaylistType.TopTracks.mTitleId), -1);
        mPlaylistList.add(topTracks);
    }


    public static final Cursor makePlaylistCursor(final Context context) {
        return context.getContentResolver().query(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI,
                new String[]{
                        BaseColumns._ID,
                        PlaylistsColumns.NAME
                }, null, null, MediaStore.Audio.Playlists.DEFAULT_SORT_ORDER);
    }

    public static void deletePlaylists(Context context, long playlistId) {
        Uri localUri = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("_id IN (");
        localStringBuilder.append((playlistId));
        localStringBuilder.append(")");
        context.getContentResolver().delete(localUri, localStringBuilder.toString(), null);
    }
}
