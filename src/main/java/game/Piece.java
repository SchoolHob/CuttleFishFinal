package main.java.game;

import main.java.primitive.Drawable;
import main.java.vector.Vector2;

import java.awt.*;

public class Piece implements Drawable {
    private Vector2 drawPosition;
    private int size;
    //private string name;

    public Piece(int size) {
        this.size = size;
    }
    public Piece setDrawPosition(Vector2 drawPosition) {this.drawPosition = drawPosition; return this;}
    public Vector2 getDrawPosition() {return drawPosition;}
    public void draw(Graphics g) {
        g.drawOval(drawPosition.intX(), drawPosition.intY(), size, size);
    }
}
