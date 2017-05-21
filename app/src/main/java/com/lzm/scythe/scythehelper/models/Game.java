package com.lzm.scythe.scythehelper.models;

import java.util.Date;
import java.util.List;

public class Game {
    Date date;
    List<Player> players;

    public Game(Date date, List<Player> players) {
        this.date = date;
        this.players = players;
    }
}
