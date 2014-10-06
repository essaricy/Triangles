package com.intelligence.games.triangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Triangle {

    private static final int TRIANGLE_SIDES = 3;

    private Point[] points;
    private Graphics graphics;

    public Triangle(Graphics graphics, Color color) {
        this.graphics = graphics;
        graphics.setColor(Color.YELLOW);
    }

    /**
     * @return the points
     */
    public Point[] getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(Point[] points) {
        this.points = points;
    }

    public void drawOnRectangleArea(Dimension size, int padding) {
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();
        int[] xPoints = {width/2, height/padding, width-width/padding};
        int[] yPoints = {height/padding, height - height/padding, height - height/padding};
        graphics.fillPolygon(xPoints, yPoints, TRIANGLE_SIDES);
    }

}
