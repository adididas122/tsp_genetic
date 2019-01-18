package tsp.graphic;

import tsp.Gene;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

class LineComponent extends JComponent {
    private ArrayList<Line2D.Double> lines;

    LineComponent(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        lines = new ArrayList<>();
    }

    void addLine(Gene c1, Gene c2) {
        Line2D.Double line = new Line2D.Double(c1.getX(), c1.getY(), c2.getX(), c2.getY());
        lines.add(line);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        for (Line2D.Double line : lines) {
            g.drawLine(
                    (int) line.getX1(),
                    (int) line.getY1(),
                    (int) line.getX2(),
                    (int) line.getY2()
            );
        }
    }
}