package game;

import primitive.Drawable;
import vector.Vector2;

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
    public Vector2 getCenterOffest() { return new Vector2(size / 2, size / 2).invert(); }
    public void draw(Graphics g) {
        g.drawOval(drawPosition.intX(), drawPosition.intY(), size, size);
    }

}
