package com.example.gobblet_gobblers_game;

public class Player {
    private int[] gobblets;
    private String color;

    public Player(String color) {
        this.gobblets = new int[]{1, 1, 2, 2, 3, 3};
        this.color = color;
    }

    public int[] getGobblets() {
        return this.gobblets;
    }

    public String getColor() {
        return this.color;
    }
}