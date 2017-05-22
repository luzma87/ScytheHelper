package com.lzm.score.helper.helpers;

import com.lzm.score.helper.R;

public class PlayerColorHelper {
    public static int playerColor(String playerColor) {
        switch (playerColor) {
            case "Blue - Nordic":
            case "Azul - Nordic":
                return R.color.player_blue;
            case "Black - Saxony":
            case "Negro - Saxony":
                return R.color.player_black;
            case "White - Polania":
            case "Blanco - Polania":
                return R.color.player_white;
            case "Yellow - Crimea":
            case "Amarillo - Crimea":
                return R.color.player_yellow;
            case "Red - Rusviet":
            case "Rojo - Rusviet":
                return R.color.player_red;
            default:
                return 0;
        }
    }
}
