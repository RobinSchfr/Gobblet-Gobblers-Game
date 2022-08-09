package com.example.gobblet_gobblers_game;

import java.util.Stack;

public class GameField {
    private Stack[][] field;

    public GameField() {
        this.field = new Stack[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.field[i][j] = new Stack<Gobblet>();
            }
        }
    }

    private Stack getStack(int squareNr) {
        return this.field[squareNr / 3][squareNr % 3];
    }

    public Gobblet getTopOfStack(int squareNr) {
        if (getStack(squareNr).empty()) {
            return null;
        }
        return (Gobblet) getStack(squareNr).peek();
    }

    public boolean isValidMove(int squareNr, Gobblet newGobblet) {
        Gobblet currentGobblet = getTopOfStack(squareNr);
        if (currentGobblet == null) {
            return true;
        }
        return newGobblet.getNumber() > currentGobblet.getNumber();
    }

    public void setGobblet(int squareNr, Gobblet newGobblet) {
        getStack(squareNr).push(newGobblet);
    }

    public void removeGobblet(int squareNr) {
        getStack(squareNr).pop();
    }

    public int getSize() {
        int size = 0;
        for (int i = 0; i < 9; i++) {
            size += getStack(i).size();
        }
        return size;
    }
}