package main.java.game;

import main.java.MouseHandler;
import main.java.Clickable;
import main.java.primitive.Drawable;
import main.java.primitive.Grid;
import main.java.vector.Vector2;
import main.java.vector.Vector3;

import java.awt.*;

public class Board implements Drawable, Clickable {
    private Vector3 position;
    private int size;
    private int rows, cols;
    private Grid grid;
    private Piece[][] pieces;

    public Board(Vector3 position, int size) {
        this.position = position;
        this.size = size;
        this.rows = 8;
        this.cols = 8;

        grid = new Grid(rows, rows, size, position);
        pieces = new Piece[rows][cols];

        MouseHandler.getInstance().addClickable(this);
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
    }

    public void placePiece(Piece piece, int x, int y) {
        pieces[x][y] = piece; // DESTRUCTIVE
    }

    public void onClick(float mouseX, float mouseY) {
        int x = (int) ((mouseX - position.x) / size);
        int y = (int) ((mouseY - position.y) / size);

        if (x < 0 || x >= cols || y < 0 || y >= rows) {
            return;
        }
        if (pieces[x][y] != null) {
            System.out.println("Piece pressed");
        }
    }

}
