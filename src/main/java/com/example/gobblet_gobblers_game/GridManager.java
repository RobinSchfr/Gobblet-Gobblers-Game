package com.example.gobblet_gobblers_game;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;

public class GridManager {
    public static final Color GRID_COLOR = Color.LIGHTGRAY;
    public static final int GRID_STROKE_WIDTH = 2;

    private Group grid;
    private Point2D[] gridPoints;
    private Game game;
    private Controller controller;

    public GridManager(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
        this.gridPoints = new Point2D[9];
        this.createGrid();
    }


    private void createGrid() {
        this.grid = new Group();
        Line l1, l2;
        double gridSize = View.WINDOW_WIDTH;
        for (int i = 1; i < 3; i++) {
            l1 = new Line(gridSize / 3 * i, 0, gridSize / 3 * i, gridSize);   // vertical lines
            l2 = new Line(0, gridSize / 3 * i, gridSize, gridSize / 3 * i);   // horizontal lines
            l1.setStrokeWidth(GRID_STROKE_WIDTH);
            l2.setStrokeWidth(GRID_STROKE_WIDTH);
            l1.setStroke(GRID_COLOR);
            l2.setStroke(GRID_COLOR);
            l1.setStrokeType(StrokeType.OUTSIDE);
            l2.setStrokeType(StrokeType.OUTSIDE);
            this.grid.getChildren().addAll(l1, l2);
        }

        int currentSquare = 0;
        for (int i = 1; i < 6; i += 2) {
            for (int j = 1; j < 6; j += 2) {
                this.gridPoints[currentSquare] = new Point2D(gridSize / 6 * j, gridSize / 6 * i);
                currentSquare++;
            }
        }
    }

    public void setGobbletOnSquare(int squareNr, String color, int number) {
        Point2D position = this.gridPoints[squareNr];
        GobbletImageView gobblet = new GobbletImageView(new Image(String.format(View.IMAGES_GOBBLETS, color, number)), color, number);
        gobblet.setScaleX(0.7);
        gobblet.setScaleY(0.7);
        gobblet.setX(position.getX() - gobblet.getImage().getWidth() / 2);
        gobblet.setY(position.getY() - gobblet.getImage().getWidth() / 2);

        this.controller.makeDraggable(gobblet);
        this.grid.getChildren().add(gobblet);
    }

    public int getSquare(double mouseX, double mouseY) {
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

    public void removeGobbletFromGrid(GobbletImageView gobblet){
        this.grid.getChildren().remove(gobblet);
    }

    public Group getGrid() {
        return this.grid;
    }
}