package com.intelligence.games.triangle;

import java.awt.Point;

public class TriangleDetail {

    private Point pointA;

    private Point pointB;

    private Point pointC;

    private boolean activated;

    private int triangleNumber;

    public TriangleDetail(Point pointA, Point pointB, Point pointC) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
    }

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    public Point getPointC() {
        return pointC;
    }

    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public int getTriangleNumber() {
        return triangleNumber;
    }

    public void setTriangleNumber(int triangleNumber) {
        this.triangleNumber = triangleNumber;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     *
     * @return a <code>String</code> representation 
     * of this object.
     */
    public String toString() {
        final String SEPARATOR = ", ";
        StringBuilder retValue = new StringBuilder();
        retValue.append("TriangleDetail ( ")
            .append(super.toString()).append(SEPARATOR)
            .append("activated = ").append(this.activated).append(SEPARATOR)
            .append("pointA = ").append(this.pointA).append(SEPARATOR)
            .append("pointB = ").append(this.pointB).append(SEPARATOR)
            .append("pointC = ").append(this.pointC).append(SEPARATOR)
            .append("triangleNumber = ").append(this.triangleNumber).append(SEPARATOR)
            .append(" )");
        return retValue.toString();
    }

}
