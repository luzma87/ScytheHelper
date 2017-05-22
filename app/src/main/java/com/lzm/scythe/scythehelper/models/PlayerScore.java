package com.lzm.scythe.scythehelper.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PlayerScore implements Parcelable {

    private Player player;
    private int popularity;
    private int coins;
    private int stars;
    private int territories;
    private int resources;
    private int structures;

    public PlayerScore(Player player) {
        this.player = player;
    }

    public PlayerScore(Parcel in) {
        player = in.readParcelable(Player.class.getClassLoader());
        int[] ints = in.createIntArray();
        popularity = ints[0];
        coins = ints[1];
        stars = ints[2];
        territories = ints[3];
        resources = ints[4];
        structures = ints[5];
    }

    public Player getPlayer() {
        return player;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setTerritories(int territories) {
        this.territories = territories;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public void setStructures(int structures) {
        this.structures = structures;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(player, flags);
        dest.writeIntArray(new int[]{popularity, coins, stars, territories, resources, structures});
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
