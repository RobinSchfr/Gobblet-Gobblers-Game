package com.example.gobblet_gobblers_game;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View {
    public static final Color PLAYER1_STORAGE_COLOR = Color.LIGHTCORAL;
    public static final Color PLAYER2_STORAGE_COLOR = Color.LIGHTBLUE;
    public static final String BACKGROUND_COLOR = "#191919";
    public static final String IMAGE_ICON = "file:src/main/resources/images/icon_red_blue.png";
    public static final String TITLE = "Gobblet Gobblers";
    public static final double GOBBLET_STORAGE_SCALE = 0.3;
    public static final int BORDER_RADIUS = 20;
    public static final int MAIN_VBOX_SPACING = 10;
    public static final int STORAGE_PADDING = -70;
    public static final int STORAGE_SPACING = -150;
    public static final int WINDOW_HEIGHT = 900;
    public static final int WINDOW_WIDTH = 650;

    private Controller controller;
    private GridManager gridManager;
    private HBox storage1;
    private HBox storage2;
    private Scene scene;
    private final Game game;
    private final Stage stage;

    public View(Stage stage, Game game, Controller controller) {
        this.stage = stage;
        this.game = game;
        this.controller = controller;
        gridManager = new GridManager(game, controller);
        drawGUI();
    }

    private void drawGUI() {
        VBox main = new VBox(MAIN_VBOX_SPACING);
        storage1 = new HBox(STORAGE_SPACING);
        storage1.setPadding(new Insets(STORAGE_PADDING));
        storage2 = new HBox(STORAGE_SPACING);
        storage2.setPadding(new Insets(STORAGE_PADDING));
        main.getChildren().addAll(gridManager.getGrid(), storage1, storage2);
        Gobblet gobblet;
        HBox storage;
        for (Player player : game.getPlayers()) {
            for (Gobblet gob : player.getGobblets()) {
                gobblet = new Gobblet(player.getColor(), gob.getNumber(), true);
                if (player == game.getPlayer1()) {
                    storage = storage1;
                } else {
                    storage = storage2;
                }
                gobblet.getImgView().setScaleX(GOBBLET_STORAGE_SCALE);
                gobblet.getImgView().setScaleY(GOBBLET_STORAGE_SCALE);
                storage.getChildren().add(gobblet.getImgView());
                controller.makeDraggable(gobblet.getImgView());
            }
        }
        storage1.setBackground(new Background(new BackgroundFill(PLAYER1_STORAGE_COLOR, new CornerRadii(BORDER_RADIUS), null)));
        storage2.setBackground(new Background(new BackgroundFill(PLAYER2_STORAGE_COLOR, new CornerRadii(BORDER_RADIUS), null)));
        scene = new Scene(main, WINDOW_WIDTH, WINDOW_HEIGHT, Color.web(BACKGROUND_COLOR));
        stage.setTitle(TITLE);
        stage.getIcons().add(new Image(IMAGE_ICON));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        controller.makeDropzone(scene);
    }

    public void disableStorage1() {
        storage1.setDisable(true);
        storage1.setOpacity(0.3);
        storage2.setDisable(false);
        storage2.setOpacity(1);
    }

    public void disableStorage2() {
        storage2.setDisable(true);
        storage2.setOpacity(0.3);
        storage1.setDisable(false);
        storage1.setOpacity(1);
    }

    public HBox getStorage1() {
        return storage1;
    }

    public HBox getStorage2() {
        return storage2;
    }

    public GridManager getGridManager() {
        return gridManager;
    }
}