package Styles;

import java.awt.*;
import java.util.logging.Logger;

public class Style {
    public static final Style DEFAULT = new Style(Color.BLACK, Color.WHITE);

    protected Color fill;
    protected Color outline;

    protected Style(Color fill, Color outline) {
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


    public static StyleBuilder builder () {
        return new StyleBuilder();
    }

    public static class StyleBuilder {
        protected static final Logger logger = Logger.getLogger(StyleBuilder.class.getName());
        protected Color fill;
        protected Color outline;

        public StyleBuilder() {
            fill = DEFAULT.getFill();
            outline = DEFAULT.getOutline();
        }

        public StyleBuilder fill(Color fill){
            this.fill = fill;
            return this;
        }
        public StyleBuilder outline(Color outline){
            this.outline = outline;
            return this;
        }

        public Style build() {
            return new Style(fill, outline);
        }

    }

}
