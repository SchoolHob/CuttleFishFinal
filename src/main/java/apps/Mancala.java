package apps;

import Styles.Style;
import game.*;
import primitive.Triangle;
import vector.Vector3;

import javax.swing.*;
import java.awt.*;

public class Mancala implements IGame {
    private static final Style mancalaStyle = Style.builder().fill(new Color(44, 30, 35)).outline(new Color(91, 41, 61)).build();

    private final GamePanel gamePanel = new GamePanel(600, 600, mancalaStyle);

    Triangle triangle;

    Board board; // Step defs for this
    //Player player1, player2;
    int player1Score;
    int player2Score;

    boolean playersTurn;

    public Mancala() {

        //triangle = new Triangle(Vector3.at(100, 100), Vector3.at(200, 100), Vector3.at(100, 200));
        board = new Board(new Vector3(250, 150), 35);
        board.placePiece(new Piece(24), 4,6 );
        board.placePiece(new Piece(24, Style.builder().fill(Color.RED).outline(Color.WHITE).build()), 5,2 );


        gamePanel.include(board);
    }


    public JPanel getJPanel() {
        return gamePanel;
    }

    int i = 0;
    public void update() {
        i++;
    }

}

