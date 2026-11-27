package apps;

import Styles.GridStyle;
import Styles.Style;
import game.*;
import vector.Vector3;

import javax.swing.*;
import java.awt.*;

public class Mancala implements IApp, IGame {
    private static final Style mancalaStyle = Style.builder()
            .fill(new Color(25, 40, 35))
            .outline(new Color(48, 91, 41)).build();
    private static final int MARBLE_SIZE = 20;

    private final GamePanel gamePanel = new GamePanel(600, 600, mancalaStyle);


    private MancalaBoard board; // Step defs for this
    private Player player1, player2;
    private boolean isPlayerOneTurn;

    public Mancala() { // Players will be inputed here
        initializePlayerData();

        board = new MancalaBoard(this, Vector3.at(130, 200), 60, GridStyle.builder()
                .fill(new Color(82, 55, 41))
                .secondaryFill(new Color(119, 70, 43))
                .outline(new Color(58, 18, 23))
                .build());

        populateBoard(board);

        gamePanel.include(board, player1, player2);
    }

    private void populateBoard(MancalaBoard board) {
        for (int i = 0; i <= board.P2_STORE_INDEX; i++) {
            if (i == board.P1_STORE_INDEX || i == board.P2_STORE_INDEX) {
                continue;
            }

            for (int j = 0; j < 4; j++) {
                board.placePiece(makeRandomMarble(), i);
            }
        }
    }

    private Piece makeRandomMarble() {
        float[] hues = {
                0.04f, 0.06f, 0.08f, 0.10f,
                0.12f, 0.14f, 0.17f, 0.20f,
                0.26f, 0.36f, 0.42f, 0.46f
        };

        float hue = hues[(int) (Math.random() * hues.length)];
        float saturation = 0.55f;
        float brightness = 0.70f;

        Color fill = Color.getHSBColor(hue, saturation, brightness);

        fill = new Color(
                fill.getRed(),
                fill.getGreen(),
                fill.getBlue(),
                180 + (int) (Math.random() * 51));

        return new Piece(MARBLE_SIZE, Style.builder()
                .fill(fill)
                .outline(fill.darker())
                .build());
    }

    public void initializePlayerData() {
        player1 = new Player("John", Style.builder()
                .fill(new Color(82, 155, 41))
                .brightOutline().build());
        player2 = new Player("Mike", Style.builder()
                .fill(new Color(152, 65, 131))
                .brightOutline()
                .build());

        player1.setIconPosition(Vector3.at(15, 15));
        player2.setIconPosition(Vector3.at(15, 45));
        player1.setIconSize(20);
        player2.setIconSize(20);

        isPlayerOneTurn = true;
        player1.setFocus(isPlayerOneTurn);
        player2.setFocus(!isPlayerOneTurn);

    }


    public JPanel getJPanel() {
        return gamePanel;
    }

    int i = 0;
    public void update() {
        i++;
    }

    @Override
    public void nextTurn() {
        isPlayerOneTurn = !isPlayerOneTurn;

        if (isPlayerOneTurn) {
            player2.endTurn();
            player1.startTurn();
        } else {
            player1.endTurn();
            player2.startTurn();
        }

        player1.setFocus(isPlayerOneTurn);
        player2.setFocus(!isPlayerOneTurn);
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }

    @Override
    public Player getCurrentPlayer() {
        return isPlayerOneTurn ? player1 : player2;
    }
}

