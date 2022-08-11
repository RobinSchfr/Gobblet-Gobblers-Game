package com.example.gobblet_gobblers_game;

import javafx.scene.image.Image;

public class Gobblet {
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
        this.squarePos = -1;
        if (addImageView)
            this.loadImageView();
    }

    private void loadImageView() {
        this.gobbletImageView = new GobbletImageView(new Image(String.format(View.IMAGES_GOBBLETS, color, number)), this);
        this.gobbletImageView.setScaleX(View.GOBBLET_SCALE);
        this.gobbletImageView.setScaleY(View.GOBBLET_SCALE);
    }

    public String getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public void setImage(GobbletImageView gobbletImageView){
        this.gobbletImageView = gobbletImageView;
    }

    public GobbletImageView getImgView() {
        return this.gobbletImageView;
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