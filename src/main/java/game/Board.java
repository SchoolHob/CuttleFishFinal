package game;

import Styles.GridStyle;
import Styles.Style;
import input.Clickable;
import input.MouseHandler;
import primitive.Drawable;
import primitive.Grid;
import vector.IntVector2;
import vector.Vector2;
import vector.Vector3;

import java.awt.*;

public class Board implements Drawable, Clickable {
    private static final IntVector2 DEFAULT_ROWS_COLS = new IntVector2(8, 8);
    private Vector3 position;
    private int size;
    private int rows, cols;
    private Grid grid;
    private Piece[][] pieces;
    private Piece pieceInHand;
    private IntVector2 pieceInHandPos;


    public Board(int rows, int cols, Vector3 position, int size, GridStyle gridStyle) {
        this.position = position;
        this.size = size;
        this.rows = rows;
        this.cols = cols;

        grid = new Grid(rows, rows, size, position);
        grid.setStyle(gridStyle);
        reset();

        MouseHandler.getInstance().addClickable(this);
    }

    public Board(Vector3 position, int size, GridStyle gridStyle) {
        this(DEFAULT_ROWS_COLS.x, DEFAULT_ROWS_COLS.y, position, size, gridStyle);
    }

    public Board(Vector3 position, int size) {
        this(position, size, GridStyle.builder().build());
    }

    public void reset() {
        pieces = new Piece[rows][cols];
        pieceInHand = null;
    }

    public void setGridStyle(GridStyle gridStyle) {
        grid.setStyle(gridStyle);
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

        //g.drawRect(position.intx(), position.inty(), size*rows, size*cols);
        // ^^^ Grid already draws this ^^^
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

    public void pickUpPiece(int x, int y) {
        pieceInHand = removePiece(x, y);
        pieceInHandPos = new IntVector2(x, y);
    }
    public void placePieceWithHand(int x, int y) {
        //placePiece returns null if there is no piece on the square OR returns the piece on that square
        pieceInHand = placePiece(pieceInHand, x, y);
        pieceInHandPos = new IntVector2(x, y);
    }

    public void onClick(float mouseX, float mouseY) {
        int x = (int) ((mouseX - position.x) / size);
        int y = (int) ((mouseY - position.y) / size);

        if (x < 0 || x >= cols || y < 0 || y >= rows) {
            return;
        }

        if (pieceInHand != null) { // Piece IS in hand
            if (pieceInHand.canMove(pieceInHandPos.x - x, pieceInHandPos.y - y)) {
                placePieceWithHand(x, y);
            } else {
                placePiece(pieceInHand, pieceInHandPos.x, pieceInHandPos.y);
                pieceInHand = null;
            }
        } else if (pieces[x][y] != null) {
            pickUpPiece(x, y);

        }

    }



}
