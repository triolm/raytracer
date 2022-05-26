import javax.swing.*;

import cameras.PerspectiveCamera;
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

        PerspectiveCamera cam = new PerspectiveCamera(camPos, camForward, new Vector(0, 1, 0), 20, 1);

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
                System.out.println(e.getKeyCode());
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
                    // double a = .2;
                    // camForward = new Vector(
                    // Math.cos(a * camForward.getDX()) - Math.sin(a * camForward.getDY()),
                    // Math.sin(a * camForward.getDX()) + Math.cos(a * camForward.getDY()),
                    // camForward.getDZ());
                    // break;
                    // case 69:
                    // double a2 = -.2;
                    // camForward = new Vector(camForward.getDX() * Math.cos(a2) -
                    // camForward.getDY() * Math.sin(
                    // a2), camForward.getDX() * Math.sin(a2) + camForward.getDY()
                    // * Math.cos(a2),
                    // camForward.getDZ());
                    // System.out.println(camForward.getDX());
                    // break;
                    default:
                        return;
                }

                PerspectiveCamera cam = new PerspectiveCamera(camPos, camForward, new Vector(0, 1, 0), 20, 1);

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
