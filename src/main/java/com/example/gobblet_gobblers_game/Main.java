package com.example.gobblet_gobblers_game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        M_Game game = new M_Game();
        C_Controller controller = new C_Controller(game);
        V_View view = new V_View(stage, game, controller);
        game.setView(view);
        controller.setView(view);
    }

    public static void main(String[] args) {
        launch();
    }
}