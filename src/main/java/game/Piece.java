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
    public void draw(Graphics g) {
        style.fill(g);
        g.fillOval(drawPosition.intX(), drawPosition.intY(), size, size);
        style.outline(g);
        g.drawOval(drawPosition.intX(), drawPosition.intY(), size, size);
    }

}
