package com.lzm.scythe.scythehelper.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PlayerScore implements Parcelable {

    Player player;
    int popularity;

    public PlayerScore(Player player, int popularity) {
        this.player = player;
        this.popularity = popularity;
    }

    public PlayerScore(Parcel in) {
        player = in.readParcelable(Player.class.getClassLoader());
        popularity = in.readInt();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(player, flags);
        dest.writeInt(popularity);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PlayerScore createFromParcel(Parcel in) {
            return new PlayerScore(in);
        }

        public PlayerScore[] newArray(int size) {
            return new PlayerScore[size];
        }
    };

}
