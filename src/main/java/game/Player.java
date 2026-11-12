package game;

import Styles.Style;
import primitive.Circle;
import primitive.Drawable;
import primitive.StyledCircle;
import vector.Vector3;

import java.awt.*;

public class Player implements Drawable {
    private String name;
    private int points;
    private boolean isPlayersTurn;
    private StyledCircle icon;

    public Player(String name, Style style) {
        this.name = name;
        this.points = 0;
        icon = new StyledCircle(new Circle(10), style);
    }

    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }

    public void givePoints(int pts) {
        points += pts;
    }

    public boolean isPlayersTurn() {
        return isPlayersTurn;
    }

    public void setPlayersTurn(boolean playersTurn) {
        isPlayersTurn = playersTurn;
    }

    public void setIconPosition(Vector3 pos) {
        icon.setPosition(pos);
    }

    public void setStyle(Style style) {
        icon.setStyle(style);
    }

    @Override
    public void draw(Graphics g) {
        icon.draw(g);
    }
}
