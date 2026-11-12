package Styles;

import java.awt.*;

public class Style {
    public static final Style DEFAULT = new Style(Color.BLACK, Color.WHITE);

    protected Color fill;
    protected Color outline;

    public Style(Color fill, Color outline) {
        this.fill = fill;
        this.outline = outline;
    }

    public void fill(Graphics g) {
        g.setColor(fill);
    }

    public void outline(Graphics g) {
        g.setColor(outline);
    }

    public Color getFill() { return fill; }
    public Color getOutline() { return outline; }

    public void setFill(Color fill) { this.fill = fill; }
    public void setOutline(Color outline) { this.outline = outline; }
}
