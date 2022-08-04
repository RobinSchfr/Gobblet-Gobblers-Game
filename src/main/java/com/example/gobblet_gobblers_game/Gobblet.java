package com.example.gobblet_gobblers_game;

public class Gobblet {
    private String color;
    private int number;

    public Gobblet(String color, int number) {
        this.color = color;
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }
}