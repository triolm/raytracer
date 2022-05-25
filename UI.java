
import javax.swing.*;

import images.ColorImage;
import geometry.*;
import java.awt.event.*;
import java.awt.Image;

public class UI {
    static Point camPos;
    static Vector camForward;

    public static void launch() throws Exception {
        camPos = new Point(0, 0, 0);
        camForward = new Vector(0, 0, -1);
        int xRes = 128;
        int yRes = 128;
        int scale = 4;

        JFrame f = new JFrame();// creating instance of JFrame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(xRes * scale + 100, yRes * scale + 100);

        JLabel imageLabel = new JLabel();
        JPanel imageHolder = new JPanel();
        imageHolder.add(imageLabel);
        f.add(imageHolder);

        Camera cam = new Camera(camPos, camForward, new Vector(0, 1, 0), 20, 1);

        Scene s = SceneCreator.UIScene(xRes, yRes, cam);
        ColorImage image = s.render(xRes, yRes, 1);

        imageLabel.setIcon(
                new ImageIcon(
                        image.toBufferedImage().getScaledInstance(xRes * scale, yRes * scale, Image.SCALE_DEFAULT)));

        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // System.out.println(e.getKeyCode());
                switch (e.getKeyCode()) {
                    case 68:
                        camPos = camPos.add(cam.getRight().normalize().scale(+.5));
                        break;
                    case 65:
                        camPos = camPos.add(cam.getRight().normalize().scale(-.5));
                        break;
                    case 87:
                        camPos = camPos.add(cam.getForward().scale(.5));
                        break;
                    case 83:
                        camPos = camPos.add(cam.getForward().scale(-.5));
                        break;
                    // case 81:
                    // camForward = new Vector(camForward.getDX() - .05, camForward.getDY(),
                    // camForward.getDZ());
                    // break;
                    // case 69:
                    // camForward = new Vector(camForward.getDX() + .05, camForward.getDY(),
                    // camForward.getDZ());
                    // break;
                    default:
                        return;
                }

                Camera cam = new Camera(camPos, camForward, new Vector(0, 1, 0), 20, 1);

                Scene s = SceneCreator.UIScene(xRes, yRes, cam);
                ColorImage image = s.render(xRes, yRes, 1);
                imageLabel.setIcon(
                        new ImageIcon(image.toBufferedImage().getScaledInstance(xRes * scale,
                                yRes * scale, Image.SCALE_DEFAULT)));

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        f.setVisible(true);
    }
}
