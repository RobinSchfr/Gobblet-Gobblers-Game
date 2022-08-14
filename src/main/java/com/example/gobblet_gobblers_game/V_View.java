package com.example.gobblet_gobblers_game;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class V_View {
    public static final Color PLAYER1_STORAGE_COLOR = Color.LIGHTCORAL;
    public static final Color PLAYER2_STORAGE_COLOR = Color.LIGHTBLUE;
    public static final String BACKGROUND_COLOR = "#191919";
    public static final String IMAGE_ICON = "file:src/main/resources/images/icon_red_blue.png";
    public static final String TITLE = "Gobblet Gobblers";
    public static final double DISABLED_OPACITY = 0.3;
    public static final double GOBBLET_STORAGE_SCALE = 0.3;
    public static final int BORDER_RADIUS = 20;
    public static final int MAIN_VBOX_SPACING = 10;
    public static final int STORAGE_PADDING = -70;
    public static final int STORAGE_SPACING = -150;
    public static final int WINDOW_HEIGHT = 900;
    public static final int WINDOW_WIDTH = 650;

    private HBox storage1;
    private HBox storage2;
    private final C_Controller controller;
    private final M_Game game;
    private final Stage stage;
    private final V_GridManager gridManager;

    public V_View(Stage stage, M_Game game, C_Controller controller) {
        this.stage = stage;
        this.game = game;
        this.controller = controller;
        gridManager = new V_GridManager(controller);
        drawGUI();
        showAccessibleUI();
    }

    private void drawGUI() {
        VBox main = new VBox(MAIN_VBOX_SPACING);
        storage1 = new HBox(STORAGE_SPACING);
        storage1.setPadding(new Insets(STORAGE_PADDING));
        storage2 = new HBox(STORAGE_SPACING);
        storage2.setPadding(new Insets(STORAGE_PADDING));
        main.getChildren().addAll(gridManager.getGrid(), storage1, storage2);
        HBox storage;
        for (M_Player MPlayer : game.getPlayers()) {
            for (M_Gobblet MGobblet : MPlayer.getGobblets()) {
                if (MPlayer == game.getPlayer1()) {
                    storage = storage1;
                } else {
                    storage = storage2;
                }
                MGobblet.getImgView().setScaleX(GOBBLET_STORAGE_SCALE);
                MGobblet.getImgView().setScaleY(GOBBLET_STORAGE_SCALE);
                storage.getChildren().add(MGobblet.getImgView());
                controller.makeDraggable(MGobblet.getImgView());
            }
        }
        storage1.setBackground(new Background(new BackgroundFill(PLAYER1_STORAGE_COLOR, new CornerRadii(BORDER_RADIUS), null)));
        storage2.setBackground(new Background(new BackgroundFill(PLAYER2_STORAGE_COLOR, new CornerRadii(BORDER_RADIUS), null)));
        Scene scene = new Scene(main, WINDOW_WIDTH, WINDOW_HEIGHT, Color.web(BACKGROUND_COLOR));
        stage.setTitle(TITLE);
        stage.getIcons().add(new Image(IMAGE_ICON));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        controller.makeDropzone(scene);
    }

    public void showAccessibleUI() {
        HBox accessible, inaccessible;
        if (game.getActivePlayer() == game.getPlayer1()) {
            accessible = storage1;
            inaccessible = storage2;
        } else {
            accessible = storage2;
            inaccessible = storage1;
        }
        accessible.setDisable(false);
        accessible.setOpacity(1);
        inaccessible.setDisable(true);
        inaccessible.setOpacity(DISABLED_OPACITY);
        for (int i = 0; i < gridManager.getGrid().getChildren().size(); i++) {
            Node gridObj = gridManager.getGrid().getChildren().get(i);
            if (gridObj instanceof Line) {
                continue;
            }
            gridObj.setEffect(null);
            if (((V_GobbletImageView) gridObj).getGobblet().getColor().equals(game.getActivePlayer().getColor()) && game.getGameField().isOnTop(((V_GobbletImageView) gridObj).getGobblet())) {
                gridObj.setEffect(new DropShadow(30, V_GridManager.GRID_COLOR));
            }
        }
    }

    public HBox getStorage1() {
        return storage1;
    }

    public HBox getStorage2() {
        return storage2;
    }

    public V_GridManager getGridManager() {
        return gridManager;
    }
}