package com.example.dang.firecube.helpers;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.dang.firecube.utils.FirecubeUtils;

public class MusicPlaybackTrack implements Parcelable{

    public static final Creator<MusicPlaybackTrack> CREATOR = new Creator<MusicPlaybackTrack>() {
        @Override
        public MusicPlaybackTrack createFromParcel(Parcel in) {
            return new MusicPlaybackTrack(in);
        }

        @Override
        public MusicPlaybackTrack[] newArray(int size) {
            return new MusicPlaybackTrack[size];
        }
    };
    public long mId;
    public long mSourceId;
    public FirecubeUtils.IdType mSourceType;
    public int mSourcePosition;

    public MusicPlaybackTrack(long id, long sourceId, FirecubeUtils.IdType type, int sourcePosition ){
        mId = id;
        mSourceId = sourceId;
        mSourceType = type;
        mSourcePosition = sourcePosition;
    }

    public MusicPlaybackTrack(Parcel in){
        mId = in.readLong();
        mSourceId = in.readLong();
        mSourceType = FirecubeUtils.IdType.getTypeById(in.readInt());
        mSourcePosition = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int i) {
        destination.writeLong(mId);
        destination.writeLong(mId);
        destination.writeInt(mSourceType.mId);
        destination.writeInt(mSourcePosition);
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof MusicPlaybackTrack){
            MusicPlaybackTrack other = (MusicPlaybackTrack) o;
            if(other != null){
                return mId == other.mId
                        && mSourceId == other.mSourceId
                        && mSourceType == other.mSourceType
                        && mSourcePosition == other.mSourcePosition;
            }
        }
        return super.equals(o);
    }
}
