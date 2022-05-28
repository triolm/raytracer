import javax.swing.*;

import cameras.PerspectiveCamera;
import images.ColorImage;
import geometry.*;
import java.awt.event.*;
import java.awt.Image;

public class UI {
    static Point camPos;
    static Vector camForward;
    static PerspectiveCamera cam;
    final static double angle = 0.04908738521;

    public static void launch() throws Exception {
        camPos = new Point(0, 0, 0);
        camForward = new Vector(.01, 0, -1);
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

        cam = new PerspectiveCamera(camPos, camForward, new Vector(0, 1, 0), 20, 1);

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
                        camPos = camPos.add(cam.getRight().normalize().scale(.5));
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
                    case 81:
                        camForward = new Vector(
                                camForward.getDX() * Math.cos(-angle) - camForward.getDZ() * Math.sin(-angle),
                                camForward.getDY(),
                                camForward.getDX() * Math.sin(-angle) + camForward.getDZ() * Math.cos(-angle))
                                .normalize();
                        break;
                    case 69:
                        camForward = new Vector(
                                camForward.getDX() * Math.cos(angle) - camForward.getDZ() * Math.sin(angle),
                                camForward.getDY(),
                                camForward.getDX() * Math.sin(angle) + camForward.getDZ() * Math.cos(angle))
                                .normalize();
                        break;
                    default:
                        return;
                }

                cam = new PerspectiveCamera(camPos, camForward, new Vector(0, 1, 0), 20, 1);

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
