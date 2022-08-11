package com.example.gobblet_gobblers_game;

import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;

public class Controller {
    private View view;
    private Game game;
    private Gobblet tempDragGobblet;

    public Controller(Game game) {
        this.game = game;
    }

    public void makeDraggable(GobbletImageView gIV) {
        gIV.setOnDragDetected(mouseEvent -> {
            System.out.println("onDragDetected");
            Dragboard db = gIV.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            Image dragView = new Image(gIV.getImage().getUrl(), gIV.getImage().getWidth() * 0.7, gIV.getImage().getHeight() * 0.7, true, true);
            db.setDragView(dragView);
            content.putString(gIV.getGobblet().getColor() + "," + gIV.getGobblet().getNumber());
            tempDragGobblet = new Gobblet(gIV.getGobblet().getColor(), gIV.getGobblet().getNumber());
            tempDragGobblet.setImage(gIV);
            tempDragGobblet.setSquarePos(view.getGridManager().getSquare(mouseEvent.getSceneX(), mouseEvent.getSceneY()));
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
            if (dragEvent.getGestureSource() != scene
                    && dragEvent.getDragboard().hasString()
                    && view.getGridManager().getSquare(dragEvent.getSceneX(), dragEvent.getSceneY()) != -1
                    && game.getGameField().isValidMove(view.getGridManager().getSquare(dragEvent.getSceneX(), dragEvent.getSceneY()), tempDragGobblet)) {
                System.out.println("onDragOver " + view.getGridManager().getSquare(dragEvent.getSceneX(), dragEvent.getSceneY()));
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }

            dragEvent.consume();
        });

        scene.setOnDragDropped(dragEvent -> {
            System.out.println("onDragDropped");
            game.setFinishedTurn(true);
            Dragboard db = dragEvent.getDragboard();
            String[] gobbletValues = db.getString().split(",");
            view.getGridManager().setGobbletOnSquare(view.getGridManager().getSquare(dragEvent.getSceneX(), dragEvent.getSceneY()), gobbletValues[0], Integer.parseInt(gobbletValues[1]));
            game.getGameField().setGobblet(view.getGridManager().getSquare(dragEvent.getSceneX(), dragEvent.getSceneY()), tempDragGobblet);
            if (tempDragGobblet.getSquarePos() != -1) {
                System.out.println("Removed1!");
                game.getGameField().removeGobblet(tempDragGobblet.getSquarePos());
                System.out.println("size: " + game.getGameField().getSize());
                view.getGridManager().removeGobbletFromGrid(tempDragGobblet);
            }
        });

        scene.setOnDragExited(dragEvent -> {
            System.out.println("onDragExited");
            tempDragGobblet = null;
        });
    }

    public void setView(View view) {
        this.view = view;
    }
}