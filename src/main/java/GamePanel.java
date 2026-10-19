package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class GamePanel extends JPanel {

    private final List<Drawable> drawables = new ArrayList<>();

    public GamePanel() {
        this(600, 600);
    }


    public GamePanel(int x, int y) {
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.BLACK);
    }

    public void include(Drawable drawable) {
        drawables.add(drawable);
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
