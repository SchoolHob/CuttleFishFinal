package apps;

import Styles.GridStyle;
import Styles.Style;
import game.*;
import primitive.Triangle;
import vector.Vector3;

import javax.swing.*;
import java.awt.*;

public class Mancala implements IGame {
    private static final Style mancalaStyle = Style.builder()
            .fill(new Color(25, 40, 35))
            .outline(new Color(48, 91, 41)).build();
    private static final int MARBLE_SIZE = 20;

    private final GamePanel gamePanel = new GamePanel(600, 600, mancalaStyle);


    Triangle triangle;

    MancalaBoard board; // Step defs for this
    //Player player1, player2;
    int player1Score;
    int player2Score;

    boolean playersTurn;

    public Mancala() {
        board = new MancalaBoard(Vector3.at(130, 200), 60, GridStyle.builder()
                .fill(new Color(82, 55, 41))
                .secondaryFill(new Color(119, 70, 43))
                .outline(new Color(58, 18, 23))
                .build());


        populateBoard(board);
        gamePanel.include(board);
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


    public JPanel getJPanel() {
        return gamePanel;
    }

    int i = 0;
    public void update() {
        i++;
    }

}

