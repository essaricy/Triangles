import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Sierpinski extends Applet implements MouseMotionListener
{

    Graphics g;
    Point d, a1,b1,c1,d1, a2,b2,c2,d2, a3,b3,c3,d3;

    int depth = 0;

    public void init()
    {
        setBackground(new Color(255,255,255));
        setBackground( Color.black );
        //addMouseMotionListener(this);
    }

// this handles the mouse operations, every time you left click it steps up one depth
// every time you right click it steps down one depth
    public boolean mouseDown(Event click, int x, int y)
    {
        if (!click.metaDown()) depth += 1;
        else if (depth>0) depth -= 1;
        repaint();
        return true;
    }

// draws first triangle
    public void paint(Graphics tri)
    {
        tri.setColor( Color.YELLOW );
        // tri.setColor(new Color(-16777216));
        int xCoords[] = {10, 390, 200};
        int yCoords[] = {390, 390, 10};
        tri.drawPolygon(xCoords, yCoords, 3);

        drawTriangle(tri, new Point(10,390),new Point(390,390),new Point(200,10), depth);
    }

    public void fillPolygon()
    {

    }

// i know the coordinates were supposed to be passed in as an array but here is the draw method
    public void drawTriangle(Graphics g, Point a, Point b, Point c, int depth)
    {
        if (depth==0) return;

        depth -= 1;
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // did the array here because i couldn't get it to work if i initialize it at beginning
        int xCoords[] = {c.x, (c.x+b.x)/2, (a.x+c.x)/2};
        int yCoords[] = {b.y, (c.y+a.y)/2, (c.y+a.y)/2};
        g.drawPolygon(xCoords, yCoords, 3);

        /*if (depth == 0) {
            g.drawString("A", a.x, a.y);
            g.drawString("B", b.x, b.y);
            g.drawString("C", c.x, c.y);
        }*/

        a1 = a;
        b1 = new Point(c.x, b.y);
        c1 = new Point((a.x+c.x)/2, (c.y+a.y)/2);
        drawTriangle(g, a1, b1, c1, depth);

        
        a2 = new Point(c.x, b.y);
        b2 = b;
        c2 = new Point((c.x+b.x)/2, (c.y+a.y)/2);
        drawTriangle(g, a2, b2, c2, depth);

        a3 = new Point((a.x+c.x)/2, (c.y+a.y)/2);
        b3 = new Point((c.x+b.x)/2, (c.y+a.y)/2);
        c3 = c;
        drawTriangle(g, a3, b3, c3, depth);


        /*d1 =  new Point((a.x)/2, (a.y)/2);
        d2 =  new Point((b.x)/2, (b.y)/2);
        d3 =  new Point((c.x)/2, (c.y)/2);*/

        /*g.setColor(Color.RED);
        //System.out.println("a = " + a + ",b =" + b + ", c =" + c);
        d1 =  new Point((a.x + b.x), (a.y + b.y)/2);
        d2 =  new Point((b.x + c.x)/2, (b.y + c.y)/2);
        d3 =  new Point((c.x + a.x)/2, (c.y + a.y)/2);
        //System.out.println("d1 = " + d1 + ",d2 =" + d2 + ", d3 =" + d3);
        drawTriangle(g, d1, d2, d3, depth);*/
        

    }

    public void mouseMoved(MouseEvent mouseEvent) {
        //System.out.println("[X, Y] = [" + mouseEvent.getX() + ", " + mouseEvent.getY() + "]");
    }

    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}