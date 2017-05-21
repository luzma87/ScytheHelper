package com.lzm.scythe.scythehelper.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game implements Parcelable {
    Date date;
    List<PlayerScore> players;

    public Game(Date date, List<PlayerScore> players) {
        this.date = date;
        this.players = players;
    }

    public List<PlayerScore> getPlayers() {
        return players;
    }

    public Game(Parcel in) {
        players = new ArrayList<>();
        this.date = new Date(in.readLong());
        in.readTypedList(players, PlayerScore.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(date.getTime());
        dest.writeTypedList(players);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}
