package main.java.primitive;

import java.awt.*;

import main.java.vector.*;

public class Rectangle implements Drawable { // extends MovingObject {  private Vector2 drawPosition{get set}; public MovingObject move(vector 3) {return piece}}
    private Vector3 position;
    private Vector2 size;

    public Rectangle(Vector3 xyz, Vector2 wh) {
        position = xyz;
        size = wh;
    }

    public Rectangle(Vector3 xyz, double w, double h) {
        this(xyz, new Vector2(w, h));
    }

    public Rectangle(double w, double h) {
        this(Vector3.ZERO, new Vector2(w, h));
    }

    public Rectangle(Vector2 wh) {
        this(Vector3.ZERO, wh);
    }


    public Rectangle setPosition(Vector3 xyz) {
        position = xyz;
        return this;
    }

    public Rectangle setSize(Vector2 wh) {
        size = wh;
        return this;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void move(Vector3 v) {
        position = position.add(v);
    }

    @Override
    public void draw(Graphics g) {

        g.drawRect(position.intx(), position.inty(), size.intX(), size.intY());
    }

    public static void draw(Graphics g, Vector3 position, Vector2 size) {
        double width = size.x;
        double height = size.y;

        g.drawLine((int) position.x, (int) position.y,(int) (position.x + width), (int) position.y);
        g.drawLine((int) (position.x + width), (int) position.y, (int) (position.x + width), (int) (position.y + height));
        g.drawLine((int) (position.x + width), (int) (position.y + height), (int) position.x, (int) (position.y + height));
        g.drawLine((int) position.x, (int) (position.y + height), (int) position.x, (int) position.y);
    }
}
