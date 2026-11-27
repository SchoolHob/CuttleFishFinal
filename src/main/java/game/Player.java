package game;

import Styles.Style;
import primitive.Circle;
import primitive.Drawable;
import primitive.StyledCircle;
import primitive.Text;
import vector.Vector2;
import vector.Vector3;

import java.awt.*;

public class Player implements Drawable {
    private String name;
    private int points;
    private boolean isPlayersTurn;
    private StyledCircle icon;
    private Style style;
    private Text label;

    public Player(String name, Style style) {
        this.name = name;
        this.points = 0;
        this.style = style;
        this.icon = new StyledCircle(new Circle(10), style);
        this.label = new Text(name, Vector2.ZERO, 16, style.getFill().brighter().brighter().brighter().brighter().darker());
    }

    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }
    public void givePoints(int pts) {
        points += pts;
        label.setText(name + ": " + points);
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
        label.setPosition(pos.add(new Vector3((icon.getSize().x + 5), 6, 0)).toVector2());
    }
    public void setIconSize(int size) { icon.setSize(new Vector2(size, size)); }

    public void setStyle(Style style) {
        icon.setStyle(style);
    }
    public void setFocus(boolean isFocused) {
        if (isFocused) {
            setStyle(style);
        } else {
            setStyle(Style.builder()
                    .fill(style.getFill().darker().darker())
                    .outline(style.getOutline().darker().darker().darker())
                    .build());
        }
    }

    public Style getStyle() { return style; }

    @Override
    public void draw(Graphics g) {
        icon.draw(g);
        label.draw(g);
    }

    public boolean canInteract() {return true;}
}
