package game;

import Styles.Style;
import primitive.Drawable;
import vector.Vector2;

import java.awt.*;

public class Piece implements Drawable {
    private Vector2 drawPosition;
    private int size;
    private Style style;
    //private string name;

    public Piece(int size) {
        this(size, Style.DEFAULT);
    }
    public Piece(int size, Style style) {
        this.size = size;
        this.style = style;
    }
    public Piece setDrawPosition(Vector2 drawPosition) {this.drawPosition = drawPosition; return this;}
    public Vector2 getDrawPosition() {return drawPosition;}
    public Vector2 getCenterOffest() { return new Vector2(size / 2, size / 2).invert(); }
    public int getSize() {return size;}
    public Style getStyle() {return style;}

    public void draw(Graphics g) {
        style.fill(g);
        g.fillOval(drawPosition.intX(), drawPosition.intY(), size, size);

        style.outline(g);
        g.drawOval(drawPosition.intX(), drawPosition.intY(), size, size);
    }

    public void drawWithOffset(Graphics g, Vector2 offset) {
        style.fill(g);
        g.fillOval(drawPosition.intX() + offset.intX(), drawPosition.intY() + offset.intY(), size, size);

        style.outline(g);
        g.drawOval(drawPosition.intX() + offset.intX(), drawPosition.intY() + offset.intY(), size, size);

    }

    public boolean canMove(int deltaX, int deltaY) { // Pass in the relative change of piece position and return true if the move is valid for the piece
        return true;
    }

}
