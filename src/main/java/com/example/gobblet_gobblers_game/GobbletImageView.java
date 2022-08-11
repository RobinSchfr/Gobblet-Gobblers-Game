package com.example.gobblet_gobblers_game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GobbletImageView extends ImageView {
    private Gobblet gobblet;

    public GobbletImageView(Image img, Gobblet gobblet) {
        super(img);
        this.gobblet = gobblet;
    }

    public Gobblet getGobblet() {
        return gobblet;
    }
}