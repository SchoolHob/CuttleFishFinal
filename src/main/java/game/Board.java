package main.java.game;

import main.java.MouseHandler;
import main.java.Clickable;
import main.java.primitive.Drawable;
import main.java.primitive.Grid;
import main.java.vector.Vector2;
import main.java.vector.Vector3;

import java.awt.*;
import java.util.Optional;

public class Board implements Drawable, Clickable {
    private Vector3 position;
    private int size;
    private int rows, cols;
    private Grid grid;
    private Piece[][] pieces;
    private Piece pieceInHand;

    public Board(Vector3 position, int size) {
        this.position = position;
        this.size = size;
        this.rows = 8;
        this.cols = 8;

        grid = new Grid(rows, rows, size, position);
        reset();

        MouseHandler.getInstance().addClickable(this);
    }

    public void reset() {
        pieces = new Piece[rows][cols];
        pieceInHand = null;
    }

    public void draw(Graphics g){
        grid.draw(g);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if (pieces[i][j] != null){
                    Vector2 offset = new Vector2(6, 6);
                    pieces[i][j].setDrawPosition(new Vector2(i*size, j*size).add(Vector2.at(position.x, position.y)).add(offset)).draw(g);
                }

            }
        }

        if (pieceInHand != null){
            pieceInHand.setDrawPosition(MouseHandler.getInstance().getMousePosition().add(pieceInHand.getCenterOffest()));
            pieceInHand.draw(g);
        }
    }

    public Piece placePiece(Piece newPiece, int x, int y) {
        Piece potentialReplacedPiece = getPiece(x, y);
        pieces[x][y] = newPiece; // overwrite piece
        return potentialReplacedPiece;
    }

    public Piece getPiece(int x, int y) {
        return pieces[x][y];
    }
    public Piece removePiece(int x, int y) {
        Piece piece = getPiece(x, y);
        pieces[x][y] = null;
        return piece;
    }

    public void onClick(float mouseX, float mouseY) {
        int x = (int) ((mouseX - position.x) / size);
        int y = (int) ((mouseY - position.y) / size);

        if (x < 0 || x >= cols || y < 0 || y >= rows) {
            return;
        }
        if (pieceInHand != null) {
            pieceInHand = placePiece(pieceInHand, x, y);
        } else if (pieces[x][y] != null) {
            pieceInHand = removePiece(x, y);
        }

    }

}
