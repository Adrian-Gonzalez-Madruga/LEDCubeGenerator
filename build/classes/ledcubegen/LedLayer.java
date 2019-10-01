package ledcubegen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;

public class LedLayer {

    private light[] l = new light[9];
    private int layer;
    private int[] lightShift = {120, 60, 0};
    private int[] layerShift = {0b1, 0b10, 0b100};
    private boolean pressed = false;
    private boolean mouseStop = false;

    public LedLayer(int layer, int x, int y) { // init the lights in each layer
        this.layer = layer;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                l[(i * 3) + j] = new light(x + lightShift[j], y + lightShift[2 - i]);
            }
        }
    }

    public int[] print() { // say to print each lights state in an array
        int[] value = new int[9];
        for (int i = 0; i < 9; i++) {
            value[i] = (l[i].getState() ? 0b1 : 0b0);
            value[i] *= layerShift[layer];
        }
        return value;
    }

    public void check() { // check each light and if over click to change state of that light
        int x = (int) MouseInfo.getPointerInfo().getLocation().getX(); 
        int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
        if(!pressed && mouseStop){
            mouseStop = false;
        }
        for (int i = 0; i < 9; i++) {
            if (x >= l[i].getX() && x <= l[i].getX() + 30 && y >= l[i].getY() + 30 && y <= l[i].getY() + 60) {
                if (pressed && !mouseStop) {
                    l[i].flip();
                    mouseStop = true;
                }
            }
        }
    }

    public void setPressed(boolean mp) { // mouse 1 pressed
        pressed = mp;
    }

    public void draw(Graphics g) { // draw lights
        for (int i = 0; i < 9; i++) {
            l[i].draw(g);
        }
    }

    private class light { //this is one of 9 in a layer

        private boolean state = false;
        private int x;
        private int y;

        public light(int x, int y) { // get x,y
            this.x = x;
            this.y = y;
        }

        public void flip() { // change state
            state = !state;
        }

        public void draw(Graphics g) { // draw light
            g.setColor(new Color(0, 153, 26));
            if (state) {
                g.fillOval(x, y, 30, 30);
            } else {
                g.drawOval(x, y, 30, 30);
            }
        }

        public boolean getState() {
            return state;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }

}
