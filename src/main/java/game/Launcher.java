package game;

import vector.IntVector2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Launcher {
    private static List<JFrame> windows = new ArrayList<>();
    static final IntVector2 WINDOW_SIZE = new IntVector2(800, 600);


    public static void launchGame(IGame game) {
        openGameWindow(game);

        Timer timer = new Timer(16, event -> {
            game.draw();
            game.update();
        });

        timer.start();
    }

    public static JFrame openGameWindow(IGame game, int width, int height) {
        JFrame window = new JFrame("Cuttlefish Games");
        window.setSize(width, height);
        window.setLocationRelativeTo(null); // center on screen
        window.setVisible(true);
        //    window.add(new JLabel(new ImageIcon("src/main/resources/wall.jpeg")));
        //    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //    window.pack();
        window.add(game.getJPanel());
        window.setVisible(true);

        windows.add(window);
        return window;
    }

    public static JFrame openGameWindow(IGame game) {
        return openGameWindow(game, WINDOW_SIZE.x, WINDOW_SIZE.y);
    }

    public static void closeAll() {
        for (JFrame window : windows) {
            window.dispose();
        }
        windows.clear();
    }

    public static int getWindowCount() {
       return windows.size();
    }
}
