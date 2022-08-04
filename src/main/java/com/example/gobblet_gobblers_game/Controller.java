package com.example.gobblet_gobblers_game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.*;

public class Controller {
    private View view;
    private Game game;

    public Controller(Game game) {
        this.game = game;
    }

    public int getSquare(double mouseX, double mouseY) {
        int squareNumber = -1;
        int[] hLines = {200, 440, 645};
        int[] vLines = hLines.clone();
        if (mouseX <= vLines[0] && mouseY <= hLines[0]) {
            squareNumber = 0;
        } else if (mouseX <= vLines[1] && mouseY <= hLines[0]) {
            squareNumber = 1;
        } else if (mouseY <= hLines[0]) {
            squareNumber = 2;
        } else if (mouseX <= vLines[0] && mouseY <= hLines[1]) {
            squareNumber = 3;
        } else if (mouseX <= vLines[1] && mouseY <= hLines[1]) {
            squareNumber = 4;
        } else if (mouseY <= hLines[1]) {
            squareNumber = 5;
        } else if (mouseX <= vLines[0] && mouseY <= hLines[2]) {
            squareNumber = 6;
        } else if (mouseX <= vLines[1] && mouseY <= hLines[2]) {
            squareNumber = 7;
        } else if (mouseY <= hLines[2]) {
            squareNumber = 8;
        }
        return squareNumber;
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


                //view.getMain().getChildren().get(0).getChi;
                

                mouseEvent.consume();
            }
        });

        gIV.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                System.out.println("onDragDone");
                dragEvent.consume();
                if (gIV.getColor().equals("red")) {
                    view.getStorage1().getChildren().remove(gIV);
                } else {
                    view.getStorage2().getChildren().remove(gIV);
                }
            }
        });
    }

    public void makeDropzone(Scene scene) {
        scene.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getGestureSource() != scene
                        && dragEvent.getDragboard().hasString()
                        && getSquare(dragEvent.getSceneX(), dragEvent.getSceneY()) != -1) {
                    System.out.println("onDragOver " + getSquare(dragEvent.getSceneX(), dragEvent.getSceneY()));
                    dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                dragEvent.consume();
            }
        });
    }

    public void addView(View view) {
        this.view = view;
    }
}