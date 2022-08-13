package com.example.gobblet_gobblers_game;

public class M_Game {
    public static final String PLAYER_COLOR_1 = "red";
    public static final String PLAYER_COLOR_2 = "blue";

    private M_Player activeMPlayer;
    private V_View view;
    private boolean finishedTurn;
    private boolean isRunning;
    private final M_GameField gameField;
    private final M_Player player1;
    private final M_Player player2;

    public M_Game() {
        isRunning = true;
        player1 = new M_Player(PLAYER_COLOR_1);
        player2 = new M_Player(PLAYER_COLOR_2);
        gameField = new M_GameField(this);
        toggleActivePlayer();
    }

    public M_Player[] getPlayers() {
        return new M_Player[]{player1, player2};
    }

    public M_Player getPlayer1() {
        return player1;
    }

    public M_Player getActivePlayer() {
        return activeMPlayer;
    }

    public void toggleActivePlayer() {
        if (activeMPlayer == player1) {
            activeMPlayer = player2;
        } else {
            activeMPlayer = player1;
        }
    }

    public boolean isFinishedTurn() {
        return finishedTurn;
    }

    public void setFinishedTurn(boolean finishedTurn) {
        this.finishedTurn = finishedTurn;
    }

    public M_GameField getGameField() {
        return gameField;
    }

    public void setView(V_View view) {
        this.view = view;
    }

    public V_View getView() {
        return view;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}