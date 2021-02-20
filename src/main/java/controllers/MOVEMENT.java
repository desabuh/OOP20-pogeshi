package controllers;

import java.util.function.UnaryOperator;

import models.GameMap.Point2D;


public enum MOVEMENT {

    /**
     * UP sub a unit to yAxis movement.
     */
    UP(p -> p.sum(-1, 0)),

    /**
     * DOWN add a unit to yAxis movement.
     */
    DOWN(p -> p.sum(1, 0)),

    /**
     * LEFT sub a unit to xAxis movement.
     */
    LEFT(p -> p.sum(0, -1)),

    /**
     * RIGTH add a unit to xAxis movement.
     */
    RIGTH(p -> p.sum(0, 1));

    private UnaryOperator<Point2D> offsetFunction;

    MOVEMENT(final UnaryOperator<Point2D> offsetFunction) {
        this.offsetFunction = offsetFunction;
    }

    public Point2D getDestFor(final Point2D point) {
        return this.offsetFunction.apply(point);
    }
}
