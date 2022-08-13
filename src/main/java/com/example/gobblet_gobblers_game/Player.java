package com.example.gobblet_gobblers_game;

public class Player {
    private final Gobblet[] gobblets;
    private final String color;

    public Player(String color) {
        gobblets = new Gobblet[6];
        for (int i = 1; i <= 6; i++) {
            gobblets[i - 1] = new Gobblet(color, (int) Math.round(i / 2.0), true);
        }
        this.color = color;
    }

    public Gobblet[] getGobblets() {
        return gobblets;
    }

    public String getColor() {
        return color;
    }
}