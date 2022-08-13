package com.example.gobblet_gobblers_game;

import javafx.scene.image.Image;

public class Gobblet {
    public static final String IMAGES_GOBBLETS = "file:src/main/resources/images/gobblet_%s_%d.png";

    private GobbletImageView gobbletImageView;
    private String color;
    private int number;
    private int squarePos;

    public Gobblet(String color, int number) {
        this(color, number, false);
    }

    public Gobblet(String color, int number, boolean addImageView) {
        this.color = color;
        this.number = number;
        squarePos = -1;
        if (addImageView) {
            loadImageView();
        }
    }

    private void loadImageView() {
        gobbletImageView = new GobbletImageView(new Image(String.format(IMAGES_GOBBLETS, color, number)), this);
    }

    public String getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public void setImage(GobbletImageView gobbletImageView) {
        this.gobbletImageView = gobbletImageView;
    }

    public GobbletImageView getImgView() {
        return gobbletImageView;
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