package com.example.gobblet_gobblers_game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Game game = new Game();
        Controller controller = new Controller(game);
        View view = new View(stage, game, controller);
        game.setView(view);
        controller.setView(view);
    }

    public static void main(String[] args) {
        launch();
    }
}