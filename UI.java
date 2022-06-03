import javax.swing.*;

import cameras.PerspectiveCamera;
import images.ColorImage;
import geometry.*;
import java.awt.event.*;
import java.awt.Component;
import java.awt.Image;
import java.awt.FlowLayout;

public class UI {
    static Point camPos;
    static Vector camForward;
    static PerspectiveCamera cam;
    final static double angle = 0.04908738521;
    static String currentScene = "0";
    static int xRes, yRes, scale;

    public static void launch() throws Exception {
        camPos = new Point(0, 0, 0);
        camForward = new Vector(.01, 0, -1);
        xRes = 128;
        yRes = 128;
        scale = 4;

        // configure j frame
        JFrame f = new JFrame();// creating instance of JFrame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(xRes * scale + 100, yRes * scale + 100);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.PAGE_AXIS));

        JButton options = new JButton("Options");
        // https://www.dreamincode.net/forums/topic/196154-when-i-add-buttons-keylistener-stops-working/
        options.setFocusable(false);
        // https://stackoverflow.com/questions/2560784/how-to-center-elements-in-the-boxlayout-using-center-of-the-element
        options.setAlignmentX(Component.CENTER_ALIGNMENT);
        f.add(options);

        // image container
        JLabel imageLabel = new JLabel();
        JPanel imageHolder = new JPanel();
        imageHolder.add(imageLabel);
        f.add(imageHolder);

        JDialog dialog = new JDialog(f, "ee");
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.PAGE_AXIS));

        dialog.setSize(350, 150);

        String[] sceneNames = { "0", "1", "2", "3" };
        JComboBox<String> scenes = new JComboBox<String>(sceneNames);
        JButton scenesButton = new JButton("Apply");
        JPanel scenesContainer = new JPanel();
        scenesContainer.setLayout(new FlowLayout());
        scenesContainer.add(new JLabel("Scene"));
        scenesContainer.add(scenes);
        scenesContainer.add(scenesButton);

        dialog.add(scenesContainer);

        SpinnerNumberModel xmodel = new SpinnerNumberModel(xRes, 10, 1920, 10);
        JSpinner xSpinner = new JSpinner(xmodel);
        SpinnerNumberModel ymodel = new SpinnerNumberModel(yRes, 10, 1080, 10);
        JSpinner ySpinner = new JSpinner(ymodel);
        JButton resButton = new JButton("Apply");
        JPanel resContainer = new JPanel();
        resContainer.setLayout(new FlowLayout());
        resContainer.add(new JLabel("Resolution"));
        resContainer.add(xSpinner);
        resContainer.add(ySpinner);
        resContainer.add(resButton);

        dialog.add(resContainer);

        SpinnerNumberModel scaleModel = new SpinnerNumberModel(4, 1, 20, 1);
        JSpinner scaleSpinner = new JSpinner(scaleModel);
        JButton scaleButton = new JButton("Apply");
        JPanel scaleContainer = new JPanel();
        scaleContainer.setLayout(new FlowLayout());
        scaleContainer.add(new JLabel("Scale"));
        scaleContainer.add(scaleSpinner);
        scaleContainer.add(scaleButton);

        dialog.add(scaleContainer);

        cam = new PerspectiveCamera(camPos, camForward, new Vector(0, 1, 0), 20, 1);

        scenesButton.addActionListener(e -> {
            currentScene = (String) scenes.getSelectedItem();
            camPos = new Point(0, 0, 0);
            camForward = new Vector(.01, 0, -1);
            render(imageLabel);
        });
        resButton.addActionListener(e -> {
            xRes = (int) xSpinner.getValue();
            yRes = (int) ySpinner.getValue();
            System.out.println(xRes + " " + yRes);
            render(imageLabel);
        });
        scaleButton.addActionListener(e -> {
            scale = (int) scaleSpinner.getValue();
            render(imageLabel);
        });
        options.addActionListener(e -> {
            dialog.setVisible(true);
        });

        render(imageLabel);

        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
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
                    case 37:
                        camForward = camForward.rotateY(-angle);
                        break;
                    case 39:
                        camForward = camForward.rotateY(angle);
                        break;
                    case 40:
                        camPos = camPos.add(cam.getUp().scale(-.5));
                        break;
                    case 38:
                        camPos = camPos.add(cam.getUp().scale(.5));
                        break;
                    default:
                        return;
                }

                render(imageLabel);

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        f.setVisible(true);
    }

    public static void render(JLabel imageLabel) {
        cam = new PerspectiveCamera(camPos, camForward, new Vector(0, 1, 0), 20, (double) xRes / yRes);

        Scene s;
        switch (currentScene) {
            case "1":
                s = SceneCreator.UIScene2(xRes, yRes, cam);
                break;
            // case "2":
            // s = SceneCreator.cubeTest(xRes, yRes, cam);
            // break;
            default:
            case "0":
                s = SceneCreator.UIScene(xRes, yRes, cam);
                break;

        }
        ColorImage image = s.render(xRes, yRes, 1);
        imageLabel.setIcon(
                new ImageIcon(
                        image.toBufferedImage().getScaledInstance(xRes * scale, yRes * scale, Image.SCALE_DEFAULT)));
    }
}
