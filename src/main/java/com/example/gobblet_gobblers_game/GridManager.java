package com.example.gobblet_gobblers_game;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;

public class GridManager {
    public static final Color GRID_COLOR = Color.LIGHTGRAY;
    public static final double GOBBLET_GRID_SCALE = 0.7;
    public static final int AMOUNT_SQUARES = 9;
    public static final int GRID_STROKE_WIDTH = 2;

    private Group grid;
    private final Controller controller;
    private final Point2D[] gridPoints;

    public GridManager(Controller controller) {
        this.controller = controller;
        gridPoints = new Point2D[AMOUNT_SQUARES];
        createGrid();
    }

    private void createGrid() {
        grid = new Group();
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
            grid.getChildren().addAll(l1, l2);
        }
        int currentSquare = 0;
        for (int i = 1; i < 6; i += 2) {
            for (int j = 1; j < 6; j += 2) {
                gridPoints[currentSquare] = new Point2D(gridSize / 6 * j, gridSize / 6 * i);
                currentSquare++;
            }
        }
    }

    public Gobblet setGobbletOnSquare(int squareNr, String color, int number) {
        Point2D position = gridPoints[squareNr];
        Gobblet gobblet = new Gobblet(color, number, true);
        gobblet.getImgView().setScaleX(GOBBLET_GRID_SCALE);
        gobblet.getImgView().setScaleY(GOBBLET_GRID_SCALE);
        gobblet.getImgView().setX(position.getX() - gobblet.getImgView().getImage().getWidth() / 2);
        gobblet.getImgView().setY(position.getY() - gobblet.getImgView().getImage().getWidth() / 2);
        grid.getChildren().add(gobblet.getImgView());
        controller.makeDraggable(gobblet.getImgView());
        return gobblet;
    }

    public void removeGobbletFromGrid(Gobblet gobblet) {
        grid.getChildren().remove(gobblet.getImgView());
    }

    public void drawWinningLine(int[] winCondition) {
        Line l = new Line(gridPoints[winCondition[0]].getX(), gridPoints[winCondition[0]].getY(), gridPoints[winCondition[2]].getX(), gridPoints[winCondition[2]].getY());
        l.setStrokeWidth(GRID_STROKE_WIDTH * 2);
        l.setStroke(GRID_COLOR);
        l.setStrokeType(StrokeType.OUTSIDE);
        grid.getChildren().add(l);
    }

    public Group getGrid() {
        return grid;
    }
}