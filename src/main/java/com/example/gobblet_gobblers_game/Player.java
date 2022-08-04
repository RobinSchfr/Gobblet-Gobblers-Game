package com.example.gobblet_gobblers_game;

public class Player {
    private Gobblet[] gobblets;
    private String color;

    public Player(String color) {
        this.gobblets = new Gobblet[6];
        for (int i = 1; i <= 6; i++) {
            this.gobblets[i - 1] = new Gobblet(color, (int) Math.round(i/2.0));
        }
        this.color = color;
    }

    public Gobblet[] getGobblets() {
        return this.gobblets;
    }

    public String getColor() {
        return this.color;
    }
}