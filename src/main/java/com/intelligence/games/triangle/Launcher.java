package com.intelligence.games.triangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JApplet;

public class Launcher extends JApplet {
    private static final int TRIANGLE_SIDES = 3;
    private static final int PADDING = 10;
    int theta = 45;
    public void paint( Graphics graphics ) {
        /*int Width = size().width;
        int Height = size().height;
        int width = Width/2;
        int height = Height/2;
        int x = (Width - width)/2;
        int y = (Height- height)/2;
        int [] polyx =  { 0, Width/2, Width, Width/2 };
        int [] polyy =  { Height/2, 0, Height/2, Height };
        Polygon poly = new Polygon( polyx, polyy, 4 );
        
        g.setColor( Color.black );
        g.fillRect( 0, 0, size().width, size().height );
        g.setColor( Color.yellow );
        g.fillPolygon( poly );
        g.setColor( Color.red );
        g.fillRect( x, y, width, height );
        g.setColor( Color.green );
        g.fillOval( x, y, width, height );
        g.setColor( Color.blue );
        int delta = 90;
        g.fillArc( x, y, width, height, theta, delta );
        g.setColor( Color.white );
        g.drawLine( x, y, x+width, x+height );*/

        int width = getWidth();
        int height = getHeight();
        graphics.setColor( Color.BLACK);
        graphics.fillRect( 0, 0, width, height);

        drawTraiangle(graphics, Color.YELLOW, getSize());
    }
    private void drawTraiangle(Graphics graphics, Color yellow, Dimension size) {
        //Dimension size = getSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();

        /*Triangle baseTriangle = new Triangle(graphics, Color.YELLOW);

        baseTriangle.drawOnRectangleArea(size, PADDING);*/

        graphics.setColor(Color.YELLOW);
        int[] xPoints = {width/2, height/PADDING, width-width/PADDING};
        int[] yPoints = {height/PADDING, height - height/PADDING, height - height/PADDING};
        graphics.fillPolygon(xPoints, yPoints, TRIANGLE_SIDES);

        //doPartition(graphics, xPoints, yPoints);
        //Triangle triangle = getTrianglePoints(graphics, yellow, xPoints, yPoints);
        drawInnerTraiangle(graphics, yellow, xPoints, yPoints);
        /*for (int index=0; index<1; index++) {
            graphics.setColor(Color.YELLOW);
        }*/
    }

    private void doPartition(Graphics graphics, int[] baseTriangleX, int[] baseTriangleY) {
        graphics.setColor(Color.RED);
        Point[] trianglePoints = new Point[] {
                new Point(baseTriangleX[0], baseTriangleY[0]),
                new Point(baseTriangleX[1], baseTriangleY[1]),
                new Point(baseTriangleX[2], baseTriangleY[2])
        };

        Point[][] partitionPoints = getPartitionPoints(trianglePoints, 2);
        for (int index = 0; index < partitionPoints.length; index++) {
            Point[] partitionPoint =  partitionPoints[index];
            graphics.drawLine((int)partitionPoint[0].getX(), (int)partitionPoint[0].getY(),
                    (int)partitionPoint[1].getX(), (int)partitionPoint[1].getY());
        }
        
    }
    private Point[][] getPartitionPoints(Point[] trianglePoints, int partitions) {
        Point point1 = null;
        Point point2 = null;

        Point[][] partitionPoints = new Point[TRIANGLE_SIDES][2];

        for (int index = 0; index < TRIANGLE_SIDES; index++) {
            partitionPoints[index] = new Point[2];

            for (int jindex = 0; jindex < 2; jindex++) {
                partitionPoints[index][jindex] = new Point();

                point1 = trianglePoints[index];
                if (index == TRIANGLE_SIDES - 1) {
                    point2 = trianglePoints[0];
                } else {
                    point2 = trianglePoints[index + 1];
                }
                partitionPoints[index][jindex].setLocation(
                        point1.getX() + point2.getX()/2,
                        point1.getX() + point2.getY()/2);
            }
        }
        /*for (int index = 0; index < TRIANGLE_SIDES; index++) {
            point1 = trianglePoints[index];
            if (index == TRIANGLE_SIDES - 1) {
                point2 = trianglePoints[0];
            } else {
                point2 = trianglePoints[index + 1];
            }

            System.out.println("point1 " + point1);
            System.out.println("point2 " + point2);

            for (int jindex=0; jindex < partitions; jindex++) {
                partitionPoints[index] = new Point[2];

                partitionPoints[index][0] = new Point();
                partitionPoints[index][0].setLocation(point1.getX()/partitions, point1.getY()/partitions);

                partitionPoints[index][1] = new Point();
                partitionPoints[index][1].setLocation(point2.getX()/partitions, point2.getY()/partitions);
            }
        }*/
        return partitionPoints;
    }
    private Point[][] getPartitionPoints(Point trianglePoint1, Point trianglePoint2, int partitions) {
        System.out.println("trianglePoint1 " + trianglePoint1);
        System.out.println("trianglePoint2 " + trianglePoint2);
        int divisor = partitions + 1;
        Point[][] partitionPoints = new Point[divisor][];

        for (int index=0; index < partitions; index++) {
            partitionPoints[index] = new Point[2];

            partitionPoints[index][0] = new Point();
            partitionPoints[index][0].setLocation(trianglePoint1.getX()/divisor, trianglePoint1.getY()/divisor);

            partitionPoints[index][1] = new Point();
            partitionPoints[index][1].setLocation(trianglePoint2.getX()/divisor, trianglePoint2.getY()/divisor);
        }

        return partitionPoints;
    }

    private void drawInnerTraiangle(Graphics graphics, Color yellow, int[] baseTriangleX, int[] baseTriangleY) {
        graphics.setColor(Color.RED);
        Point[] midPoints = getMidPointOf(baseTriangleX, baseTriangleY);
        System.out.println("baseTriangleX[0]/2 " + baseTriangleX[0]/2);
        int[] xPoints = {(int) midPoints[0].getX(), (int) midPoints[1].getX(), (int) midPoints[2].getX()};
        int[] yPoints = {(int) midPoints[0].getY(), (int) midPoints[1].getY(), (int) midPoints[2].getY()};
        graphics.fillPolygon(xPoints, yPoints, TRIANGLE_SIDES);

        Point[][] innerPoints = new Point[4][2];
        innerPoints[0][0] = new Point();
        innerPoints[0][0].setLocation(
                (baseTriangleX[0] + midPoints[0].getX())/2,
                (baseTriangleY[0] + midPoints[0].getY())/2);
    }

    private Point[] getMidPointOf(int[] baseTriangleX, int[] baseTriangleY) {
        Point[] points = new Point[TRIANGLE_SIDES];
        for (int index = 0; index < TRIANGLE_SIDES; index++) {
            points[index] = new Point();
            if (index == TRIANGLE_SIDES - 1) {
                points[index].setLocation(
                        (baseTriangleX[index] + baseTriangleX[0])/2,
                        (baseTriangleY[index] + baseTriangleY[0])/2);
            } else {
                points[index].setLocation(
                        (baseTriangleX[index] + baseTriangleX[index + 1])/2,
                        (baseTriangleY[index] + baseTriangleY[index + 1])/2);
            }
            
        }
        /*points[1] = new Point();
        points[1].setLocation(
                (baseTriangleX[1] + baseTriangleX[2])/2,
                (baseTriangleY[1] + baseTriangleY[2])/2);
        points[2] = new Point();
        points[2].setLocation(
                (baseTriangleX[2] + baseTriangleX[0])/2,
                (baseTriangleY[2] + baseTriangleY[0])/2);*/
        return points;
    }

    public void init() {
        setSize(710, 620);
        addMouseListener( new MouseAdapter() {
            public void mousePressed( MouseEvent e ) {
                theta = (theta + 10) % 360;
                repaint();
            }
        } );
    }

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.setVisible(true);
    }
}
