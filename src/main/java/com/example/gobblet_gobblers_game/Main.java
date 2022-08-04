package com.example.gobblet_gobblers_game;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Game game = new Game();
        Controller controller = new Controller(game);
        View view = new View(stage, game, controller);
        controller.addView(view);
    }

    public static void main(String[] args) {
        launch();
    }
}