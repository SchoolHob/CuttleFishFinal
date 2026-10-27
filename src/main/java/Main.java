package main.java;

import main.java.game.IGame;
import main.java.mancala.Mancala;
import main.java.vector.IntVector2;

import javax.swing.*;

import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());

    final IntVector2 WINDOW_SIZE = new IntVector2(800, 600);

    void main() {

        Mancala mancala = new Mancala();

        openWindow(mancala);

        Timer timer = new Timer(16, event -> {

            mancala.draw();
            mancala.update();
        });

        timer.start();
    }


    JFrame openWindow(IGame game) {
        JFrame window = new JFrame("Cuttlefish Games");
        window.setSize(WINDOW_SIZE.x, WINDOW_SIZE.y);
        window.setLocationRelativeTo(null); // center on screen
        window.setVisible(true);
    //    window.add(new JLabel(new ImageIcon("src/main/resources/wall.jpeg")));
    //    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //    window.pack();
        window.add(game.getJPanel());
        window.setVisible(true);
        return window;
    }


}