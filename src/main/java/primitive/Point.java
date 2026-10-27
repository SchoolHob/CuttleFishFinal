package main.java.primitive;

import main.java.vector.Vector3;

import java.awt.*;

public class Point implements Drawable {

    public Vector3 position;

    public Point() {
        this(0, 0, 0);
    }
    public Point(double x, double y, double z) {
        position = new Vector3(x, y, z);
    }
    public Point move(Vector3 v) {
        position = position.add(v);
        return this;
    }
    public Point setPosition(Vector3 v) {
        position = v;
        return this;
    }

    @Override
    public void draw (Graphics g) {

        g.drawOval((int) position.x, (int) position.y, 1, 1);
    }
}
