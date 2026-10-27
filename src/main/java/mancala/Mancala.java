package mancala;

import game.Board;
import game.GamePanel;
import game.IGame;
import game.Piece;
import primitive.Drawable;
import primitive.Triangle;
import primitive.Point;
import vector.Vector3;

import javax.swing.*;
import java.awt.*;

public class Mancala implements IGame {
    private final GamePanel gamePanel = new GamePanel();

    Triangle triangle;
    Board board; // Step defs for this

    public Mancala() {

        triangle = new Triangle(Vector3.at(100, 100), Vector3.at(200, 100), Vector3.at(100, 200));
        board = new Board(new Vector3(250, 150), 35);
        board.placePiece(new Piece(24), 4,6 );

        gamePanel.include(triangle, board);
    }

    public JPanel getJPanel() {
        return gamePanel;
    };


    int i = 0;
    public void update() {
        i++;
    }
}

