package com.lzm.score.helper.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Player implements Parcelable {
    private int number;
    private String name;
    private String color;

    private Date createdDate;
    private long id;

    public Player(int number, String name, String color) {
        this.number = number;
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Player(Parcel in) {
        String[] data = new String[3];

        in.readStringArray(data);
        this.id = Long.parseLong(data[0]);
        this.number = Integer.parseInt(data[1]);
        this.name = data[1];
        this.color = data[2];
        this.createdDate = new Date(Long.parseLong(data[3]));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                String.valueOf(this.id),
                String.valueOf(this.number),
                this.name,
                this.color,
                String.valueOf(this.createdDate.getTime())
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}
