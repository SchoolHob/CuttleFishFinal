package main.java;

import main.java.Mancala;

import javax.swing.*;
public class Main {
    void main() {

        Mancala mancala = new Mancala();

        openWindow(mancala.getPanel());
        Timer timer = new Timer(16, event -> {

            mancala.getPanel().repaint();
            mancala.update();
        });

        timer.start();
    }


    void openWindow(JPanel panel) {
        JFrame window = new JFrame("Game");
        window.setSize(800, 600);
        window.setLocationRelativeTo(null); // center on screen
        window.setVisible(true);

    //    window.add(new JLabel(new ImageIcon("src/main/resources/wall.jpeg")));
    //    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //    window.pack();
        window.add(panel);
        window.setVisible(true);
    }
}