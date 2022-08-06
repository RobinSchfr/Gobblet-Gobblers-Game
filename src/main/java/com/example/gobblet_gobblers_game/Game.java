package com.example.gobblet_gobblers_game;

public class Game {
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private boolean finishedTurn;
    private GameField gameField;

    public Game() {
        this.player1 = new Player("red");
        this.player2 = new Player("blue");
        this.activePlayer = this.player1;
        this.gameField = new GameField();
    }

    public Player[] getPlayers() {
        return new Player[]{this.player1, this.player2};
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public Player getActivePlayer() {
        return this.activePlayer;
    }

    public void toggleActivePlayer() {
        if (this.activePlayer == this.player1) {
            this.activePlayer = this.player2;
        } else {
            this.activePlayer = this.player1;
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