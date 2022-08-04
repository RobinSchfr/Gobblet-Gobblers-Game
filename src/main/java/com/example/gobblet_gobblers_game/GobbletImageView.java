package com.example.gobblet_gobblers_game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GobbletImageView extends ImageView {
    private String color;
    private int number;

    public GobbletImageView(Image img, String color, int number) {
        super(img);
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