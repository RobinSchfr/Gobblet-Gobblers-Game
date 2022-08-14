package com.example.gobblet_gobblers_game;

import java.util.Stack;

public class M_GameField {
    private final M_Game game;
    private final Stack[][] field;

    public M_GameField(M_Game game) {
        this.game = game;
        field = new Stack[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = new Stack<M_Gobblet>();
            }
        }
    }

    private Stack getStack(int squareNr) {
        return field[squareNr / 3][squareNr % 3];
    }

    public M_Gobblet getTopOfStack(int squareNr) {
        if (getStack(squareNr).empty()) {
            return null;
        }
        return (M_Gobblet) getStack(squareNr).peek();
    }

    public boolean isValidMove(int squareNr, M_Gobblet newMGobblet) {
        M_Gobblet currentMGobblet = getTopOfStack(squareNr);
        if (currentMGobblet == null) {
            return true;
        }
        return newMGobblet.getNumber() > currentMGobblet.getNumber();
    }

    public boolean isOnTop(M_Gobblet MGobblet) {
        for (int i = 0; i < V_GridManager.AMOUNT_SQUARES; i++) {
            if (getTopOfStack(i) == MGobblet) {
                return true;
            }
        }
        return false;
    }

    public void checkForWin() {
        int[][] winConditions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        int[] winCondition = null;
        String winColor = null;
        String[] colors;
        if (game.getActivePlayer() == game.getPlayer1()){
            colors = new String[]{M_Game.PLAYER_COLOR_1, M_Game.PLAYER_COLOR_2};
        } else {
            colors = new String[]{M_Game.PLAYER_COLOR_2, M_Game.PLAYER_COLOR_1};
        }
        for (String color : colors) {
            if (winColor != null) {
                break;
            }
            for (int i = 0; i < winConditions.length; i++) {
                int counter = 0;
                for (int j = 0; j < winConditions[1].length; j++) {
                    M_Gobblet MGobblet = getTopOfStack(winConditions[i][j]);
                    if (MGobblet != null && MGobblet.getColor().equals(color)) {
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
        }
    }

    public void setGobblet(int squareNr, M_Gobblet newMGobblet) {
        getStack(squareNr).push(newMGobblet);
    }

    public void removeGobblet(int squareNr) {
        getStack(squareNr).pop();
    }
}