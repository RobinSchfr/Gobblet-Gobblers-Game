package com.example.gobblet_gobblers_game;

import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.*;

public class Controller {
    private View view;
    private Game game;

    public Controller(Game game) {
        this.game = game;
    }

    public void makeDraggable(GobbletImageView gIV) {
        gIV.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("onDragDetected");
                Dragboard db = gIV.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(gIV.getColor() + "," + gIV.getNumber());
                db.setContent(content);
                game.setFinishedTurn(false);

                mouseEvent.consume();
            }
        });

        gIV.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                System.out.println("onDragDone");
                if (!game.isFinishedTurn()) return;
                if (gIV.getColor().equals("red")) {
                    view.getStorage1().getChildren().remove(gIV);
                } else {
                    view.getStorage2().getChildren().remove(gIV);
                }

                dragEvent.consume();
            }
        });
    }

    public void makeDropzone(Scene scene) {
        scene.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                System.out.println("onDragEntered");

            }
        });
        scene.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getGestureSource() != scene
                        && dragEvent.getDragboard().hasString()
                        && view.getGridManager().getSquare(dragEvent.getSceneX(), dragEvent.getSceneY()) != -1) {
                    System.out.println("onDragOver " + view.getGridManager().getSquare(dragEvent.getSceneX(), dragEvent.getSceneY()));
                    dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                dragEvent.consume();
            }
        });

        scene.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                System.out.println("onDragDropped");
                game.setFinishedTurn(true);

            }
        });

        scene.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                System.out.println("onDragExited");

            }
        });
    }

    public void addView(View view) {
        this.view = view;
    }
}