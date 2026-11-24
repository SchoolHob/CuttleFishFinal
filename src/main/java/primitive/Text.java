package primitive;

import vector.Vector2;

import java.awt.*;

public class Text implements Drawable {
    private static final int DEFAULT_SIZE = 20;

    private String text;
    private Vector2 position;
    private int size;
    private Color color;

    public Text(String text, Vector2 position) {
        this(text, position, DEFAULT_SIZE, Color.WHITE);
    }
    public Text(String text, Vector2 position,  Color color) {
        this(text, position, DEFAULT_SIZE, color);
    }
    public Text(String text, Vector2 position, int size, Color color) {
        this.text = text;
        this.position = position;
        this.size = size;
        this.color = color;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Vector2 getPosition() {
        return position;
    }
    public void setPosition(Vector2 position) {
        this.position = position;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.setFont(new Font("Serif", Font.PLAIN, size));
        g.drawString(text, position.intX(), position.intY());
    }
}
