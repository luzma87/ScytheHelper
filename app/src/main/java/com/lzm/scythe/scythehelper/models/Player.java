package com.lzm.scythe.scythehelper.models;

import java.util.Date;

public class Player {
    private int number;
    private String name;
    private String color;

    private Date createdDate;
    private long id;

    public Player() {
    }

    public Player(int number, String name, String color) {
        this.number = number;
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
