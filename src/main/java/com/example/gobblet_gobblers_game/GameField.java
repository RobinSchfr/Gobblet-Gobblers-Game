package com.example.gobblet_gobblers_game;

import java.util.Stack;

public class GameField {
    private Stack[][] field;

    public GameField() {
        this.field = new Stack[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.field[i][j] = new Stack<>();
            }
        }
    }
}