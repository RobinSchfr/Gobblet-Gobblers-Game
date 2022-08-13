package com.example.gobblet_gobblers_game;

public class Game {
    public static final String PLAYER_COLOR_1 = "red";
    public static final String PLAYER_COLOR_2 = "blue";

    private Player activePlayer;
    private View view;
    private boolean finishedTurn;
    private boolean isRunning;
    private final GameField gameField;
    private final Player player1;
    private final Player player2;

    public Game() {
        isRunning = true;
        player1 = new Player(PLAYER_COLOR_1);
        player2 = new Player(PLAYER_COLOR_2);
        gameField = new GameField(this);
        toggleActivePlayer();
    }

    public Player[] getPlayers() {
        return new Player[]{player1, player2};
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void toggleActivePlayer() {
        if (activePlayer == player1) {
            activePlayer = player2;
        } else {
            activePlayer = player1;
        }
    }

    public boolean isFinishedTurn() {
        return finishedTurn;
    }

    public void setFinishedTurn(boolean finishedTurn) {
        this.finishedTurn = finishedTurn;
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}