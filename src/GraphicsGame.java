
import javax.swing.*;
import java.awt.*;

public class GraphicsGame extends JPanel {

    Universe univ;
    public static int graphicsW = 550;

    public void paintComponent(Graphics g) {
        if (univ == null) {
            return;
        }
        super.paintComponent(g);
        for (int i = 0; i < Universe.size; i++) {
            for (int j = 0; j < Universe.size; j++) {
                if (Universe.universe[i][j].equals("O")) {
                    g.setColor(GameOfLife.filledCellColor);
                    g.fillRect(i * graphicsW / Universe.size, j * graphicsW / Universe.size,
                            graphicsW / Universe.size, graphicsW / Universe.size);
                } else {
                    g.setColor(GameOfLife.gridColor);
                    g.drawRect(i * graphicsW / Universe.size, j * graphicsW / Universe.size,
                            graphicsW / Universe.size, graphicsW / Universe.size);
                }
            }
        }
    }

    public void draw(Universe universe) {
        this.univ = universe;
        repaint();
    }

}