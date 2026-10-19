package main.java;

import java.awt.*;

public class Point implements Drawable {
    double x, y, z;

    public static Point at(double x, double y, double z) {
        return new Point(x, y, z);
    }
    public static Point at(double x, double y) {
        return new Point(x, y, 0);
    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(double x, double y) { this(x, y, 0);}
    public Point() { this(0, 0); }

    public void move(double dx, double dy, double dz) {
        x += dx;
        y += dy;
        z += dz;
    }

    public void move(double dx, double dy) { move(dx, dy, 0); }

    @Override
    public void draw (Graphics g) {
        g.drawOval((int) x, (int) y, 1, 1);
    }

}
