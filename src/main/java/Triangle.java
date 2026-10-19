package main.java;

import java.awt.*;

public class Triangle implements Drawable {
    Point a, b, c;
    // Point[].
    // Move() {move points]

    // Renderer
    // Configuration style = Style.wireframe();
    // class Style
    // style.set(g);


    public Triangle(Point p1, Point p2, Point p3) {
        a  = p1;
        b = p2;
        c = p3;
    }

    public void move(double dx, double dy, double dz) {
        a.move(dx, dy, dz);
        b.move(dx, dy, dz);
        c.move(dx, dy, dz);
    }

    public void move(double dx, double dy) {
        a.move(dx, dy);
        b.move(dx, dy);
        c.move(dx, dy);
    }

    @Override
    public void draw(Graphics g) {
        g.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);
        g.drawLine((int) b.x, (int) b.y, (int) c.x, (int) c.y);
        g.drawLine((int) c.x, (int) c.y, (int) a.x, (int) a.y);
    }
}
