package apps;

import game.GamePanel;
import game.IGame;

import javax.swing.*;
import java.io.Serializable;

public class PlayerSettings implements IGame {
    private final GamePanel gamePanel = new GamePanel();

    @Override
    public void update() {

    }

    @Override
    public JPanel getJPanel() {
        return null;
    }
}
