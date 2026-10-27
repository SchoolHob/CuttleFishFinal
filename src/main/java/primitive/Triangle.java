package primitive;

import vector.Vector3;

import java.awt.*;

public class Triangle implements Drawable {
    public Vector3 a, b, c;
    // Point[].
    // Move() {move points]

    // Renderer
    // Configuration style = Style.wireframe();
    // class Style
    // style.set(g);


    public Triangle(Vector3 p1, Vector3 p2, Vector3 p3) {
        a  = p1;
        b = p2;
        c = p3;
    }

    public void move(Vector3 v) {
        a = a.add(v);
        b = b.add(v);
        c = c.add(v);
    }

    @Override
    public void draw(Graphics g) {
        g.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);
        g.drawLine((int) b.x, (int) b.y, (int) c.x, (int) c.y);
        g.drawLine((int) c.x, (int) c.y, (int) a.x, (int) a.y);
    }
}
