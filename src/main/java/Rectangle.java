package main.java;

import java.awt.*;

public class Rectangle implements Drawable {
    Point a, b;
    // Point[].
    // Move() {move points]

    // Renderer
    // Configuration style = Style.wireframe();
    // class Style
    // style.set(g);


    public Rectangle(Point p1, Point p2) {
        a  = p1;
        b = p2;
    }

    public void move(double dx, double dy, double dz) {
        a.move(dx, dy, dz);
        b.move(dx, dy, dz);
    }

    public void move(double dx, double dy) {
        a.move(dx, dy);
        b.move(dx, dy);
    }

    @Override
    public void draw(Graphics g) {
        g.drawLine((int) a.x, (int) a.y, (int) b.x, (int) a.y);
        g.drawLine((int) b.x, (int) a.y, (int) b.x, (int) b.y);
        g.drawLine((int) b.x, (int) b.y, (int) a.x, (int) b.y);
        g.drawLine((int) a.x, (int) b.y, (int) a.x, (int) a.y);
    }
}
