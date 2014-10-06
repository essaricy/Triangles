package com.intelligence.games.triangle;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.quiz.ui.TrianglesGame;

public class ImagePanel extends JPanel implements MouseListener {

    private static final long serialVersionUID = 1L;

    BufferedImage image;

    private List trianglesList;

    private TrianglesGame trianglesGame;

    public ImagePanel(BufferedImage image, TrianglesGame trianglesGame) {
        this.image = image;
        this.trianglesGame = trianglesGame;

        Point[] points = {
                                                new Point(264, 6)/*0*/,
                                        new Point(196, 130)/*1*/, new Point(334, 130)/*2*/,
                                   new Point(126, 255)/*3*/, new Point(264, 255)/*4*/, new Point(403, 255)/*5*/,
                       new Point(64, 368)/*6*/, new Point(196, 368)/*7*/, new Point(335, 368)/*8*/, new Point(466, 368)/*9*/,
            new Point(6, 473)/*10*/, new Point(126, 473)/*11*/, new Point(264, 473)/*12*/, new Point(403, 523)/*13*/, new Point(466, 473)/*14*/
        };
        trianglesList = new ArrayList();
        // First row
        trianglesList.add(new TriangleDetail(points[0], points[1], points[2]));
        // Second row
        trianglesList.add(new TriangleDetail(points[1], points[3], points[4]));
        trianglesList.add(new TriangleDetail(points[1], points[2], points[4]));
        trianglesList.add(new TriangleDetail(points[2], points[4], points[5]));
        // Third row
        trianglesList.add(new TriangleDetail(points[3], points[6], points[7]));
        trianglesList.add(new TriangleDetail(points[3], points[4], points[7]));
        trianglesList.add(new TriangleDetail(points[4], points[7], points[8]));
        trianglesList.add(new TriangleDetail(points[4], points[5], points[8]));
        trianglesList.add(new TriangleDetail(points[5], points[8], points[9]));
        trianglesList.add(new TriangleDetail(points[6], points[10], points[11]));
        trianglesList.add(new TriangleDetail(points[6], points[7], points[11]));
        trianglesList.add(new TriangleDetail(points[7], points[11], points[12]));
        trianglesList.add(new TriangleDetail(points[7], points[8], points[12]));
        trianglesList.add(new TriangleDetail(points[8], points[12], points[13]));
        trianglesList.add(new TriangleDetail(points[8], points[9], points[13]));
        trianglesList.add(new TriangleDetail(points[9], points[13], points[14]));
        System.out.println("trianglesList " + trianglesList.size());

        assignNumbers();
        addMouseListener(this);
    }

    private void assignNumbers() {
        TriangleDetail detail = null;
        for (int index = 0; index < trianglesList.size(); index++) {
            detail = (TriangleDetail) trianglesList.get(index);
            detail.setTriangleNumber(index + 1);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw image centered.
        int x = (getWidth() - image.getWidth())/2;
        int y = (getHeight() - image.getHeight())/2;
        g.drawImage(image, x, y, this);
    } 

    public void mouseClicked(MouseEvent mouseEvent) {
        Point a = null;
        Point b = null;
        Point c = null;
        Point clickedPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
        TriangleDetail detail = null;
        Polygon polygon = null;
        /*if (trianglesGame.isQuestionOn()) {
            // Question is already on. Cannot show another question.
            trianglesGame.animateQuestionPanel();
            return;
        }*/
        for (int index = 0; index < trianglesList.size(); index++) {
            detail = (TriangleDetail) trianglesList.get(index);
            if (detail != null && !detail.isActivated()) {
                a = detail.getPointA();
                b = detail.getPointB();
                c = detail.getPointC();
                polygon = new Polygon(
                        new int[] {a.x, b.x, c.x},
                        new int[] {a.y, b.y, c.y}, 3);
                if (polygon.contains(clickedPoint)) {
                    break;
                }
                detail = null;
            }
        }
        // detail object comes when user clicks outside the triangle
        // or an already activated triangle.
        if (detail != null) {
            trianglesGame.setQuestion(detail);
        }
    }

    public void mouseEntered(MouseEvent mouseEvent) {}

    public void mouseExited(MouseEvent mouseEvent) {}

    public void mousePressed(MouseEvent mouseEvent) {}

    public void mouseReleased(MouseEvent mouseEvent) {
    }
}
