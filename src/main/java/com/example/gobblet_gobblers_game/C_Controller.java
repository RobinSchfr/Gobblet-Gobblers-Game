package com.example.gobblet_gobblers_game;

import javafx.event.Event;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;

public class C_Controller {
    private M_Gobblet tempDragGobblet;
    private V_View view;
    private final M_Game game;

    public C_Controller(M_Game game) {
        this.game = game;
    }

    public void makeDraggable(V_GobbletImageView gIV) {
        gIV.setOnDragDetected(mouseEvent -> {
            if (gIV.getGobblet().getColor().equals(game.getActivePlayer().getColor()) && game.isRunning()) {
                Dragboard db = gIV.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                Image dragView = new Image(gIV.getImage().getUrl(), gIV.getImage().getWidth() * V_GridManager.GOBBLET_GRID_SCALE, gIV.getImage().getHeight() * V_GridManager.GOBBLET_GRID_SCALE, true, true);
                db.setDragView(dragView);
                content.putString(gIV.getGobblet().getColor() + "," + gIV.getGobblet().getNumber());
                tempDragGobblet = new M_Gobblet(gIV.getGobblet().getColor(), gIV.getGobblet().getNumber());
                tempDragGobblet.setImage(gIV);
                tempDragGobblet.setSquarePos(getSquare(mouseEvent));
                db.setContent(content);
                game.setFinishedTurn(false);
                mouseEvent.consume();
            }
        });

        gIV.setOnDragDone(dragEvent -> {
            if (!game.isFinishedTurn()) return;
            if (gIV.getGobblet().getColor().equals("red")) {
                view.getStorage1().getChildren().remove(gIV);
            } else {
                view.getStorage2().getChildren().remove(gIV);
            }
            tempDragGobblet = null;
            dragEvent.consume();
        });
    }

    public void makeDropzone(Scene scene) {
        scene.setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() != scene && dragEvent.getDragboard().hasString() && getSquare(dragEvent) != -1 && game.getGameField().isValidMove(getSquare(dragEvent), tempDragGobblet)) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        scene.setOnDragDropped(dragEvent -> {
            game.setFinishedTurn(true);
            Dragboard db = dragEvent.getDragboard();
            String[] gobbletValues = db.getString().split(",");
            M_Gobblet newMGobblet = view.getGridManager().setGobbletOnSquare(getSquare(dragEvent), gobbletValues[0], Integer.parseInt(gobbletValues[1]));
            game.getGameField().setGobblet(getSquare(dragEvent), newMGobblet);
            if (tempDragGobblet.getSquarePos() != -1) {
                game.getGameField().removeGobblet(tempDragGobblet.getSquarePos());
                view.getGridManager().removeGobbletFromGrid(tempDragGobblet);
            }
            game.toggleActivePlayer();
            view.showAccessibleUI();
            game.getGameField().checkForWin();
            dragEvent.consume();
        });
    }

    private int getSquare(Event event) {
        double mouseX, mouseY;
        if (event instanceof DragEvent e) {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        } else {
            MouseEvent e = (MouseEvent) event;
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        }
        int squareNumber = -1;
        int[] lines = {V_View.WINDOW_WIDTH / 3, V_View.WINDOW_WIDTH / 3 * 2, V_View.WINDOW_WIDTH};
        if (mouseX <= lines[0] && mouseY <= lines[0]) {
            squareNumber = 0;
        } else if (mouseX <= lines[1] && mouseY <= lines[0]) {
            squareNumber = 1;
        } else if (mouseY <= lines[0]) {
            squareNumber = 2;
        } else if (mouseX <= lines[0] && mouseY <= lines[1]) {
            squareNumber = 3;
        } else if (mouseX <= lines[1] && mouseY <= lines[1]) {
            squareNumber = 4;
        } else if (mouseY <= lines[1]) {
            squareNumber = 5;
        } else if (mouseX <= lines[0] && mouseY <= lines[2]) {
            squareNumber = 6;
        } else if (mouseX <= lines[1] && mouseY <= lines[2]) {
            squareNumber = 7;
        } else if (mouseY <= lines[2]) {
            squareNumber = 8;
        }
        return squareNumber;
    }

    public void setView(V_View view) {
        this.view = view;
    }
}