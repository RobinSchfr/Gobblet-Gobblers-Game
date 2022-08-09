package com.example.gobblet_gobblers_game;

public class Gobblet {
    private String color;
    private int number;
    private int squarePos;

    public Gobblet(String color, int number) {
        this.color = color;
        this.number = number;
        this.squarePos = -1;
    }

    public String getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public int getSquarePos() {
        System.out.println("SquarePos: " + squarePos);
        return squarePos;
    }

    public void setSquarePos(int squarePos) {
        System.out.println("SetSquarePos: " + squarePos);
        this.squarePos = squarePos;
    }
}