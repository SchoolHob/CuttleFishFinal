package primitive;

import Styles.Style;
import vector.*;

import java.awt.*;

public class Circle implements Drawable {
    protected Vector3 position;
    protected Vector2 size;

    public Circle(Vector3 xyz, Vector2 wh) {
        position = xyz;
        size = wh;
    }

    public Circle(Vector3 xyz, double w, double h) {
        this(xyz, new Vector2(w, h));
    }

    public Circle(double w, double h) {
        this(Vector3.ZERO, new Vector2(w, h));
    }

    public Circle(double wh) {
        this(Vector3.ZERO, new Vector2(wh, wh));
    }

    public Circle setPosition(Vector3 xyz) {
        position = xyz;
        return this;
    }

    public Circle setSize(Vector2 wh) {
        size = wh;
        return this;
    }

    public StyledCircle getStyledCircle() {
        return new StyledCircle(this);
    }

    public StyledCircle getStyledCircle(Style style) {
        return new StyledCircle(this, style);
    }

    public Vector2 getSize() {
        return size;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void move(Vector3 v) {
        position = position.add(v);
    }

    @Override
    public void draw(Graphics g) {
        draw(g, position, size);
    }

    public static void draw(Graphics g, Vector3 position, Vector2 size) {
        g.drawOval(
                (int) (position.x - size.x / 2),
                (int) (position.y - size.y / 2),
                (int) size.x,
                (int) size.y
        );
    }
}
