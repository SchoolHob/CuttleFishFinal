package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Mancala {
    private final GamePanel panel = new GamePanel();
    private List<Drawable> drawables = panel.drawablesList();

    Triangle triangle;

    public Mancala() {
        triangle = new Triangle(Point.at(100, 100), Point.at(200, 100), Point.at(100, 200));
        drawables.addLast(triangle);
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 6; i++) {
                drawables.addLast(new Rectangle(Point.at(250 + i * 60, 250 + j * 60), Point.at(300 + i * 60, 300 + j * 60)));
            }
        }

        drawables.addLast(new Rectangle(Point.at(250 - 60, 250), Point.at(300 - 60, 360)));
        drawables.addLast(new Rectangle(Point.at(610, 250), Point.at(660, 360)));
    }

    int i = 0;
    public void update() {
//        if (i % 100 < 50) {
//            triangle.move (1, 1);
//        } else {
//            triangle.move (-1, -1);
//        }

        i++;
    }

    public void paint(Graphics g) {
        panel.repaint();
    }

    public JPanel getPanel() {

        return panel;
    };
}

