package ledcubegen;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class LedCubeGen { // get mouse motion listener

    private JFrame f;
    private LedLayer[] l = new LedLayer[3];
    private GraphicsPanel gp = new GraphicsPanel(l); // graphics.
    private boolean button2Switch = false;

    public LedCubeGen() {
        f = new JFrame("LedCubePanel"); // make Jframe
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(gp);
        f.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) { // if button 1 is pressed
                if (me.getButton() == MouseEvent.BUTTON1) {
                    for (int i = 0; i < 3; i++) {
                        l[i].setPressed(true);
                    }
                }
                if (me.getButton() == MouseEvent.BUTTON3 && !button2Switch) { // if button 2 is pressed sys.out binary
                    button2Switch = true;
                    int[] b1 = l[0].print();
                    int[] b2 = l[1].print();
                    int[] b3 = l[2].print();
                    for (int i = 0; i < 9; i++) {
                        System.out.print("B" + Integer.toBinaryString((b1[i] + b2[i] + b3[i])) + ", "); // convert int that values are stored in to binary
                    }
                    System.out.println("5,"); // time at end. 5 = placeholder
                }
            }

            public void mouseReleased(MouseEvent me) { // if button 1 is released
                if (me.getButton() == MouseEvent.BUTTON1) {
                    for (int i = 0; i < 3; i++) {
                        l[i].setPressed(false);
                    }
                }
                if (me.getButton() == MouseEvent.BUTTON3 && button2Switch == true) { // if button 2 is released
                    button2Switch = false;
                }
            }

        });
        for(int i = 0; i < 3; i++) {
            l[i] = new LedLayer(i, 50 + (200 * i), 100); // init ledLayer
        }
    }

    public void run() { // redraw and run the checks on light clicks
        gp.redraw();
        for(int i = 0; i < 3; i++) {
            l[i].check();
        }
    }

    public void setSize(int width, int height) { // set screen size
        f.setSize(width, height);
    }

    public static void main(String[] args) throws InterruptedException {

        LedCubeGen f = new LedCubeGen(); // make overlay
        f.setSize(700, 400);

        while (true) { // run code
            f.run();
            Thread.sleep(20);
        }

    }

}
