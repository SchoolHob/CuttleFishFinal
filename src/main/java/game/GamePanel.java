package main.java.game;

import main.java.ClickHandler;
import main.java.primitive.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    private final List<Drawable> drawables = new ArrayList<>();

    public GamePanel() {
        this(600, 600);
        ClickHandler.getInstance().includePanel(this);
    }


    public GamePanel(int x, int y) {
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.BLACK);
    }

    public void include(Drawable drawable) {
        drawables.add(drawable);
    }
    public void include(Drawable ... drawables) {
        for (Drawable drawable : drawables) {
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

        g.setColor(Color.WHITE);
        for (Drawable drawable : drawables) {
            drawable.draw(g);
        }
    }
}
