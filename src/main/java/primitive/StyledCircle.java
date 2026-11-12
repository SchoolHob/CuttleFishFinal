package primitive;

import Styles.Style;
import vector.Vector2;
import vector.Vector3;

import java.awt.*;

public class StyledCircle extends Circle {
    private Style style;

    public StyledCircle(Vector3 xyz, Vector2 wh, Style style) {
        super(xyz, wh);
        this.style = style;
    }

    public StyledCircle(Circle circle, Style style) {
        this(circle.getPosition(), circle.getSize(), style);
    }

    public StyledCircle(Circle circle) {
        this(circle, Style.DEFAULT);
    }

    public StyledCircle(Vector3 xyz, Vector2 wh) {
        this(xyz, wh, Style.DEFAULT);
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) (position.x - size.x / 2);
        int y = (int) (position.y - size.y / 2);
        int w = (int) size.x;
        int h = (int) size.y;

        style.fill(g);
        g.fillOval(x, y, w, h);

        style.outline(g);
        g.drawOval(x, y, w, h);
    }
}