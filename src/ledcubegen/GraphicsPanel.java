package ledcubegen;

import java.awt.Color;
import javax.swing.JComponent;

public class GraphicsPanel extends JComponent { // draw
    
    LedLayer[] l;
    
    public GraphicsPanel(LedLayer l[]) { //link layer
        this.l = l;
    }
    
    @Override
    public void paintComponent(java.awt.Graphics g) { // graphics
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, 700, 400);
        for(int i = 0; i < 3; i++) {
            l[i].draw(g);
        }
    }
    
    public void redraw() { // redraw in main
        this.repaint();
    }
    
}
