package com.example.gobblet_gobblers_game;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class View {
    public static final String TITLE = "Gobblet Gobblers";
    public static final String IMAGE_ICON = "file:src/main/resources/images/icon_red_blue.png";
    public static final String IMAGES_GOBBLETS = "file:src/main/resources/images/gobblet_%s_%d.png";
    public static final String BACKGROUND_COLOR = "#191919";
    public static final Color GRID_COLOR = Color.LIGHTGRAY;
    public static final Color PLAYER1_STORAGE_COLOR = Color.LIGHTCORAL;
    public static final Color PLAYER2_STORAGE_COLOR = Color.LIGHTBLUE;
    public static final int WINDOW_WIDTH = 650;
    public static final int WINDOW_HEIGHT = 890;
    public static final int MAIN_VBOX_SPACING = 10;
    public static final int GRID_STROKE_WIDTH = 2;
    public static final int STORAGE_SPACING = -150;
    public static final int STORAGE_PADDING = -70;
    public static final double GRID_SCALE_FACTOR = 8.1;

    private final Stage stage;
    private final Game game;
    private Controller controller;

    private HBox storage1;
    private HBox storage2;
    private VBox main;

    public View(Stage stage, Game game, Controller controller) {
        this.stage = stage;
        this.game = game;
        this.controller = controller;
        this.drawGUI();
    }

    private void drawGUI() {
        main = new VBox(MAIN_VBOX_SPACING);
        // draws grid
        Group grid = new Group();
        Line l1, l2;
        for (int i = 1; i < 3; i++) {
            l1 = new Line(30 * GRID_SCALE_FACTOR * i, 5 * GRID_SCALE_FACTOR, 30 * GRID_SCALE_FACTOR * i, 85 * GRID_SCALE_FACTOR);
            l2 = new Line(5 * GRID_SCALE_FACTOR, 30 * GRID_SCALE_FACTOR * i, 85 * GRID_SCALE_FACTOR, 30 * GRID_SCALE_FACTOR * i);
            l1.setStrokeWidth(GRID_STROKE_WIDTH);
            l2.setStrokeWidth(GRID_STROKE_WIDTH);
            l1.setStroke(GRID_COLOR);
            l2.setStroke(GRID_COLOR);
            l1.setStrokeType(StrokeType.OUTSIDE);
            l2.setStrokeType(StrokeType.OUTSIDE);
            grid.getChildren().addAll(l1, l2);
        }

        this.storage1 = new HBox(STORAGE_SPACING);
        this.storage1.setPadding(new Insets(STORAGE_PADDING));
        this.storage1.setMinWidth(20);

        this.storage2 = new HBox(STORAGE_SPACING);
        this.storage2.setPadding(new Insets(STORAGE_PADDING));

        main.getChildren().addAll(grid, this.storage1, this.storage2);

        // draws gobblet storages
        ImageView gobblet;
        HBox storage;
        for (Player player : this.game.getPlayers()) {
            for (int number : player.getGobblets()) {
                gobblet = this.loadGobbletImage(player.getColor(), number);
                if (player == this.game.getPlayer1()) {
                    storage = this.storage1;
                } else {
                    storage = this.storage2;
                }
                storage.getChildren().add(gobblet);
            }
        }
        this.storage1.setBackground(new Background(new BackgroundFill(PLAYER1_STORAGE_COLOR, new CornerRadii(20), null)));
        this.storage2.setBackground(new Background(new BackgroundFill(PLAYER2_STORAGE_COLOR, new CornerRadii(20), null)));

        // stage setup
        Scene scene = new Scene(main, WINDOW_WIDTH, WINDOW_HEIGHT, Color.web(BACKGROUND_COLOR));
        this.stage.setTitle(TITLE);
        this.stage.getIcons().add(new Image(IMAGE_ICON));
        this.stage.setResizable(false);
        this.stage.setScene(scene);
        this.stage.show();

        scene.setOnMouseMoved(e -> System.out.println(controller.getSquare(e.getX(), e.getY())));
        controller.makeDropzone(scene);
    }

    private GobbletImageView loadGobbletImage(String color, int number) {
        GobbletImageView gobblet = new GobbletImageView(new Image(String.format(IMAGES_GOBBLETS, color, number)), color, number);
        gobblet.setScaleX(0.3);
        gobblet.setScaleY(0.3);
        controller.makeDraggable(gobblet);
        return gobblet;
    }

    public HBox getStorage1() {
        return storage1;
    }

    public HBox getStorage2() {
        return storage2;
    }

    public VBox getMain() {
        return main;
    }
}