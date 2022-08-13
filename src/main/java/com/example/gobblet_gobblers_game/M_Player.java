package com.example.gobblet_gobblers_game;

public class M_Player {
    private final M_Gobblet[] gobblets;
    private final String color;

    public M_Player(String color) {
        gobblets = new M_Gobblet[6];
        for (int i = 1; i <= 6; i++) {
            gobblets[i - 1] = new M_Gobblet(color, (int) Math.round(i / 2.0), true);
        }
        this.color = color;
    }

    public M_Gobblet[] getGobblets() {
        return gobblets;
    }

    public String getColor() {
        return color;
    }
}