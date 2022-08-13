package com.example.gobblet_gobblers_game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class V_GobbletImageView extends ImageView {
    private final M_Gobblet gobblet;

    public V_GobbletImageView(Image img, M_Gobblet gobblet) {
        super(img);
        this.gobblet = gobblet;
    }

    public M_Gobblet getGobblet() {
        return gobblet;
    }
}