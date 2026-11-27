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
    private Style style;

    public Player(String name, Style style) {
        this.name = name;
        this.points = 0;
        this.style = style;
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

    public void startTurn() {
        isPlayersTurn = true;
        onTurnStart();
    }
    public void endTurn() {
        isPlayersTurn = false;
        onTurnEnd();
    }

    protected void onTurnStart() {}
    protected void onTurnEnd() {}

    public void setIconPosition(Vector3 pos) {
        icon.setPosition(pos);
    }

    public void setStyle(Style style) {
        icon.setStyle(style);
    }
    public void setFocus(boolean isFocused) {
        if (isFocused) {
            setStyle(style);
        } else {
            setStyle(Style.builder()
                    .fill(style.getFill().darker())
                    .outline(style.getOutline().darker().darker())
                    .build());
        }
    }

    @Override
    public void draw(Graphics g) {
        icon.draw(g);
    }

    public boolean canInteract() {return true;}
}
