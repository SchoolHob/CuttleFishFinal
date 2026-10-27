package main.java.mancala;

import main.java.MouseHandler;
import main.java.game.Board;
import main.java.game.GamePanel;
import main.java.game.IGame;
import main.java.game.Piece;
import main.java.primitive.Drawable;
import main.java.primitive.Triangle;
import main.java.primitive.Point;

import main.java.vector.Vector3;

import javax.swing.*;
import java.awt.*;

public class Mancala implements IGame {
    private final GamePanel gamePanel = new GamePanel();

    Triangle triangle;
    Board board; // Step defs for this
    Drawable mousePointer;

    public Mancala() {

        triangle = new Triangle(Vector3.at(100, 100), Vector3.at(200, 100), Vector3.at(100, 200));
        board = new Board(new Vector3(275, 250), 35);
        board.placePiece(new Piece(24), 4,6 );
        mousePointer = new Point();

        gamePanel.include(triangle, board, mousePointer);
    }

    public JPanel getJPanel() {
        return gamePanel;
    };


    int i = 0;
    public void update() {
        ((Point)mousePointer).setPosition(MouseHandler.getInstance().getMousePosition().toVector3());
        i++;
    }
}

