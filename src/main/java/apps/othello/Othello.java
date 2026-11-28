package apps.othello;

import Styles.Style;
import game.GamePanel;
import apps.IApp;
import game.IGame;
import game.Player;

import javax.swing.*;
import java.awt.*;

public class Othello implements IApp, IGame {
    private static final Style mancalaStyle = Style.builder()
            .fill(new Color(25, 40, 35))
            .outline(new Color(48, 91, 41)).build();

    private final GamePanel gamePanel = new GamePanel(600, 600, mancalaStyle);

    @Override
    public void update() {

    }

    @Override
    public JPanel getJPanel() {
        return null;
    }

    @Override
    public Player getPlayer1() {
        return null;
    }

    @Override
    public Player getPlayer2() {
        return null;
    }

    @Override
    public Player getCurrentPlayer() {
        return null;
    }

    @Override
    public void nextTurn() {

    }
}
