package com.example.gobblet_gobblers_game;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View {
    public static final String TITLE = "Gobblet Gobblers";
    public static final String IMAGE_ICON = "file:src/main/resources/images/icon_red_blue.png";
    public static final String IMAGES_GOBBLETS = "file:src/main/resources/images/gobblet_%s_%d.png";
    public static final String BACKGROUND_COLOR = "#191919";
    public static final Color PLAYER1_STORAGE_COLOR = Color.LIGHTCORAL;
    public static final Color PLAYER2_STORAGE_COLOR = Color.LIGHTBLUE;
    public static final int WINDOW_WIDTH = 650;
    public static final int WINDOW_HEIGHT = 900;
    public static final int MAIN_VBOX_SPACING = 10;
    public static final int STORAGE_SPACING = -150;
    public static final int STORAGE_PADDING = -70;
    public static final double GOBBLET_SCALE = 0.3;

    private final Stage stage;
    private final Game game;
    private Controller controller;
    private GridManager gridManager;

    private HBox storage1;
    private HBox storage2;
    private Scene scene;

    public View(Stage stage, Game game, Controller controller) {
        this.stage = stage;
        this.game = game;
        this.controller = controller;
        this.gridManager = new GridManager(this.game, this.controller);
        this.drawGUI();
    }

    private void drawGUI() {
        VBox main = new VBox(MAIN_VBOX_SPACING);

        this.storage1 = new HBox(STORAGE_SPACING);
        this.storage1.setPadding(new Insets(STORAGE_PADDING));

        this.storage2 = new HBox(STORAGE_SPACING);
        this.storage2.setPadding(new Insets(STORAGE_PADDING));

        main.getChildren().addAll(gridManager.getGrid(), this.storage1, this.storage2);

        // draws gobblet storages
        Gobblet gobblet;
        HBox storage;
        for (Player player : this.game.getPlayers()) {
            for (Gobblet gob : player.getGobblets()) {
                gobblet = new Gobblet(player.getColor(), gob.getNumber(), true);
                if (player == this.game.getPlayer1()) {
                    storage = this.storage1;
                } else {
                    storage = this.storage2;
                }
                controller.makeDraggable(gobblet.getImgView());
                storage.getChildren().add(gobblet.getImgView());
            }
        }
        this.storage1.setBackground(new Background(new BackgroundFill(PLAYER1_STORAGE_COLOR, new CornerRadii(20), null)));
        this.storage2.setBackground(new Background(new BackgroundFill(PLAYER2_STORAGE_COLOR, new CornerRadii(20), null)));

        // stage setup
        scene = new Scene(main, WINDOW_WIDTH, WINDOW_HEIGHT, Color.web(BACKGROUND_COLOR));
        this.stage.setTitle(TITLE);
        this.stage.getIcons().add(new Image(IMAGE_ICON));
        this.stage.setResizable(false);
        this.stage.setScene(scene);
        this.stage.show();
        scene.setOnMouseMoved(e -> System.out.println(gridManager.getSquare(e.getX(), e.getY())));
        controller.makeDropzone(scene);
    }

    public void disableStorage1(){
        this.storage1.setDisable(true);
        this.storage1.setOpacity(0.3);
        this.storage2.setDisable(false);
        this.storage2.setOpacity(1);
    }

    public void disableStorage2(){
        this.storage2.setDisable(true);
        this.storage2.setOpacity(0.3);
        this.storage1.setDisable(false);
        this.storage1.setOpacity(1);
    }

    public HBox getStorage1() {
        return storage1;
    }

    public HBox getStorage2() {
        return storage2;
    }

    public GridManager getGridManager() {
        return this.gridManager;
    }
}