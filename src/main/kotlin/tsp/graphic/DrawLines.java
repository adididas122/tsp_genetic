package tsp.graphic;

import tsp.Gene;

import javax.swing.*;
import java.util.List;

public class DrawLines {

    public static void draw( List<Gene> genes) {
        LineComponent lineComponent = new LineComponent(400, 400);
        for (int a = 0; a < genes.size() - 1; a++) {
            lineComponent.addLine(
                    genes.get(a),
                    genes.get(a + 1)
            );
        }
        lineComponent.addLine(
                genes.get(0),
               genes.get(genes.size() - 1));
        JOptionPane.showMessageDialog(null, lineComponent);
    }
}
