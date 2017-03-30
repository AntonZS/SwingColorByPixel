
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by anton on 13.03.2017.
 */
public class Guis {
    static int red;
    static int green;
    static int blue;
    static int[] lab;
    static int x;
    static int y;

    static JFrame f;

    static JLabel l1;
    static JLabel l2;
    static JLabel l3 ;
    static JLabel l4;
    static JLabel l5;
    static JLabel l6 ;



    public static void main(String[] args) {

        Guis guis = new Guis();
        guis.go();

    }

    private static void go() {
        f = new JFrame("Color");
        f.setSize(300, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPanel mp = new MyDrawPanel();
        f.getContentPane().add(BorderLayout.CENTER, mp);
        f.addKeyListener(new ColorListener());

        MyDrawPanel panel = new MyDrawPanel();

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        l1 = new JLabel("R");
        l2 = new JLabel("G");
        l3 = new JLabel("B");

        l4 = new JLabel("L");
        l5 = new JLabel("a");
        l6 = new JLabel("b");

        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(l4);
        panel.add(l5);
        panel.add(l6);

        f.getContentPane().add(BorderLayout.SOUTH,panel);
    }

    private static class MyDrawPanel extends JPanel {
        public void paintComponent(Graphics g) {

            g.setColor(new Color(red, green, blue));
            g.fillRect(100, 100, 100, 100);
        }
    }

    private static class ColorListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getExtendedKeyCode() == 32) {

                Point location = MouseInfo.getPointerInfo().getLocation();
                x = (int) location.getX();
                y = (int) location.getY();

                Robot r = null;
                try {
                    r = new Robot();
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }
                Color color = r.getPixelColor(x, y);

                CIELab Lab = new CIELab();



                red = color.getRed();
                green = color.getGreen();
                blue = color.getBlue();

                lab = Lab.rgb2lab(red,green, blue);

            }

            l1.setText("R: "+red);
            l2.setText("G: "+green);
            l3.setText("B: "+blue);
            l4.setText("L: "+lab[0]);
            l5.setText("a: "+lab[1]);
            l6.setText("b: "+lab[2]);

            f.repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
