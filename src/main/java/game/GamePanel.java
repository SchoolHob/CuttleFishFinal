package game;

import Styles.Style;
import input.MouseHandler;
import primitive.Drawable;
import vector.IntVector2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GamePanel extends JPanel {
    static Logger logger = Logger.getLogger(GamePanel.class.getName());
    private static final IntVector2 DEFAULT_SIZE = new IntVector2(600, 600);

    private final List<Drawable> drawables = new ArrayList<>();
    private Style style;

    public GamePanel() {
        this(DEFAULT_SIZE.x, DEFAULT_SIZE.y);
    }

    public GamePanel(int x, int y) {
        this(x, y, Style.DEFAULT);
    }

    public GamePanel(int x, int y, Style style) {
        MouseHandler.getInstance().includePanel(this);
        this.style = style;
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(style.getFill());
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public void include(Drawable drawable) {
        drawables.add(drawable);
    }
    public void include(Drawable ... drawables) {
        for (Drawable drawable : drawables) {
            if (drawable == null) {
                logger.warning("drawable is null, skipping...");
                continue;
            }
            include(drawable);
        }
    }

    public void remove(Drawable drawable) {
        drawables.remove(drawable);
    }

    public List<Drawable> drawablesList() {return drawables;}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Background
        style.fill(g);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (Drawable drawable : drawables) {
            drawable.draw(g);
        }

        //Outline
        style.outline(g);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());
    }
}
