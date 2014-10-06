import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class TranglesFrame extends JFrame {
    private static final Dimension APP_DIMENSION = new Dimension(400,400);
    private static final int TOTAL_TRIANGLES = 16;
    private static final int ROWS = (int) Math.sqrt(TOTAL_TRIANGLES);
    private static final int COLUMNS = ROWS * 2;

    public TranglesFrame() {
        setSize(APP_DIMENSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void paint(Graphics graphics) {
        graphics.setColor(Color.RED);
        //graphics.drawString("ABC", 100, 100);

        int rectX = 30;
        int rectY = 30;
        int rectWidth = (int)APP_DIMENSION.getWidth()-40;
        int rectHeight= (int)APP_DIMENSION.getHeight()-40;
        graphics.drawRect(rectX, rectY, rectWidth, rectHeight);
        int rowSalt = rectHeight/ROWS-1;
        for (int rowIndex = 1; rowIndex < ROWS; rowIndex++) {
            int height = rowIndex * (rowSalt);
            graphics.drawLine(rectX, rectY + height, rectX + rectWidth, rectY + height);
            
        }
        int colSalt = rectWidth/COLUMNS-1;
        for (int colIndex = 1; colIndex < COLUMNS; colIndex++) {
            int width = colIndex * (colSalt);
            graphics.drawLine(rectX + width, rectY, rectX + width, rectY + rectHeight);
        }
    }

    public static void main(String[] args) {
        TranglesFrame frame = new TranglesFrame();
        frame.setVisible(true);
    }
}
