package com.example.gobblet_gobblers_game;

public class Game {
    private GameField gameField;
    private Player activePlayer;
    private Player player1;
    private Player player2;
    private boolean finishedTurn;

    public Game() {
        player1 = new Player("red");
        player2 = new Player("blue");
        gameField = new GameField();
        toggleActivePlayer();
    }

    public Player[] getPlayers() {
        return new Player[]{player1, player2};
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
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
}