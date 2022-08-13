package com.example.gobblet_gobblers_game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Stack;

public class GameField {
    private final Game game;
    private final Stack[][] field;

    public GameField(Game game) {
        this.game = game;
        field = new Stack[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = new Stack<Gobblet>();
            }
        }
    }

    private Stack getStack(int squareNr) {
        return field[squareNr / 3][squareNr % 3];
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

    public boolean isOnTop(Gobblet gobblet) {
        for (int i = 0; i < GridManager.AMOUNT_SQUARES; i++) {
            if (getTopOfStack(i) == gobblet) {
                return true;
            }
        }
        return false;
    }

    public void checkForWin() {
        int[][] winConditions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        int[] winCondition = null;
        String winColor = null;
        for (String color : new String[]{Game.PLAYER_COLOR_1, Game.PLAYER_COLOR_2}) {
            if (winColor != null) {
                break;
            }
            for (int i = 0; i < winConditions.length; i++) {
                int counter = 0;
                for (int j = 0; j < winConditions[1].length; j++) {
                    Gobblet gobblet = getTopOfStack(winConditions[i][j]);
                    if (gobblet != null && gobblet.getColor().equals(color)) {
                        counter++;
                    }
                }
                if (counter == 3) {
                    winColor = color;
                    winCondition = winConditions[i];
                    break;
                }
            }
        }
        if (winColor != null) {
            game.getView().getGridManager().drawWinningLine(winCondition);
            game.setRunning(false);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), actionEvent -> game.getView().getStage().close()));
            timeline.play();
        }
    }

    public void setGobblet(int squareNr, Gobblet newGobblet) {
        getStack(squareNr).push(newGobblet);
    }

    public void removeGobblet(int squareNr) {
        getStack(squareNr).pop();
    }
}