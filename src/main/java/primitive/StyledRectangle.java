package primitive;

import Styles.Style;
import vector.Vector2;
import vector.Vector3;

import java.awt.*;

public class StyledRectangle extends Rectangle {
    private Style style;

    public StyledRectangle(Vector3 xyz, Vector2 wh, Style style) {
        super(xyz, wh);
        this.style = style;
    }

    public StyledRectangle(Rectangle rect, Style style) {
        this(rect.getPosition(), rect.getSize(), style);
    }

    public StyledRectangle(Rectangle rect) {
        this(rect, Style.DEFAULT);
    }

    public StyledRectangle(Vector3 xyz, Vector2 wh) {
        this(xyz, wh, Style.DEFAULT);
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    @Override
    public void draw(Graphics g) {
        style.fill(g);
        g.fillRect((int) position.x, (int) position.y, (int) size.x, (int) size.y);

        style.outline(g);
        g.drawRect((int) position.x, (int) position.y, (int) size.x, (int) size.y);
    }
}
