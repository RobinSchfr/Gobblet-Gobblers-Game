package com.example.gobblet_gobblers_game;

import javafx.event.Event;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;

public class Controller {
    private Game game;
    private Gobblet tempDragGobblet;
    private View view;

    public Controller(Game game) {
        this.game = game;
    }

    public void makeDraggable(GobbletImageView gIV) {
        gIV.setOnDragDetected(mouseEvent -> {
            System.out.println("onDragDetected");
            Dragboard db = gIV.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            Image dragView = new Image(gIV.getImage().getUrl(), gIV.getImage().getWidth() * GridManager.GOBBLET_GRID_SCALE, gIV.getImage().getHeight() * GridManager.GOBBLET_GRID_SCALE, true, true);
            db.setDragView(dragView);
            content.putString(gIV.getGobblet().getColor() + "," + gIV.getGobblet().getNumber());
            tempDragGobblet = new Gobblet(gIV.getGobblet().getColor(), gIV.getGobblet().getNumber());
            tempDragGobblet.setImage(gIV);
            tempDragGobblet.setSquarePos(getSquare(mouseEvent));
            db.setContent(content);
            game.setFinishedTurn(false);
            mouseEvent.consume();
        });

        gIV.setOnDragDone(dragEvent -> {
            System.out.println("onDragDone");
            if (!game.isFinishedTurn()) return;
            if (gIV.getGobblet().getColor().equals("red")) {
                view.getStorage1().getChildren().remove(gIV);
            } else {
                view.getStorage2().getChildren().remove(gIV);
            }
            dragEvent.consume();
        });
    }

    public void makeDropzone(Scene scene) {
        scene.setOnDragEntered(dragEvent -> System.out.println("onDragEntered"));
        scene.setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() != scene && dragEvent.getDragboard().hasString() && getSquare(dragEvent) != -1 && game.getGameField().isValidMove(getSquare(dragEvent), tempDragGobblet)) {
                System.out.println("onDragOver " + getSquare(dragEvent));
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        scene.setOnDragDropped(dragEvent -> {
            System.out.println("onDragDropped");
            game.setFinishedTurn(true);
            Dragboard db = dragEvent.getDragboard();
            String[] gobbletValues = db.getString().split(",");
            view.getGridManager().setGobbletOnSquare(view.getGridManager().controller.getSquare(dragEvent), gobbletValues[0], Integer.parseInt(gobbletValues[1]));
            game.getGameField().setGobblet(view.getGridManager().controller.getSquare(dragEvent), tempDragGobblet);
            if (tempDragGobblet.getSquarePos() != -1) {
                System.out.println("Removed1!");
                game.getGameField().removeGobblet(tempDragGobblet.getSquarePos());
                System.out.println("size: " + game.getGameField().getSize());
                view.getGridManager().removeGobbletFromGrid(tempDragGobblet);
            }
            dragEvent.consume();
        });

        scene.setOnDragExited(dragEvent -> {
            System.out.println("onDragExited");
            tempDragGobblet = null;
        });
    }

    public void setView(View view) {
        this.view = view;
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
        int[] lines = {View.WINDOW_WIDTH / 3, View.WINDOW_WIDTH / 3 * 2, View.WINDOW_WIDTH};
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
}