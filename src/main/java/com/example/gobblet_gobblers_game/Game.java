package com.example.gobblet_gobblers_game;

public class Game {
    private Player player1;
    private Player player2;
    private GameField gameField;

    public Game() {
        this.player1 = new Player("red");
        this.player2 = new Player("blue");
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
}