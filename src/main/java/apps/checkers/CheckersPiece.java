package apps.checkers;

import Styles.Style;
import game.OwnedPiece;
import game.Player;

import java.awt.*;

public class CheckersPiece extends OwnedPiece {
    public static final Style RED = Style.builder()
            .fill(new Color(190, 32, 32))
            .outline(new Color(70, 9, 9))
            .build();
    public static final Style BLACK = Style.builder()
            .fill(new Color(16, 15, 15))
            .outline(new Color(25, 25, 30))
            .build();

    private static final Color KING_RING = new Color(230, 190, 60);

    private static final int SIZE = 24;

    private boolean isPlayer1;
    private boolean isKing = false;

    //Basic Constructor
    public CheckersPiece(Player owner, boolean isPlayer1) {
        super(SIZE, isPlayer1 ? RED : BLACK, owner);
        this.isPlayer1 = isPlayer1;
    }

    //Constructor allowing for personalized style to be passed.
    public CheckersPiece(Style style, Player owner, boolean isPlayer1){
        super(SIZE, style, owner);
        this.isPlayer1 = isPlayer1;
    }

    public boolean isPlayer1() { return isPlayer1; }
    public boolean isKing() {return isKing; }
    public void crownPiece() { isKing = true; }

    //Down = positive direction, up = negative direction
    //Returns -1 if is player 1 to make sure
    public int getForwardDirection() { return isPlayer1 ? 1 : -1; }


    //Check whether the given move is a valid move
    //TODO: Add Multiple jump implementation and Check for King Pieces
    @Override
    public boolean canMove(int deltaX, int deltaY) {
        if (deltaX == 0 && deltaY == 0) {return true;}
        int distance = Math.abs(deltaX);

        boolean diagonal = distance == Math.abs(deltaY);
        boolean stepOrJump = distance == 1 || distance == 2;
        boolean forward = Integer.signum(deltaY) == getForwardDirection();
        return diagonal && stepOrJump && (forward || isKing());

    }


    //Updated draw to add indicator that piece is a king
    @Override
    public void draw(Graphics g){
        super.draw(g);

        if (isKing) {
            int ring = getSize() / 2;
            int x = getDrawPosition().intX() + (getSize() - ring) / 2;
            int y = getDrawPosition().intY() + (getSize() - ring) / 2;

            g.setColor(KING_RING);
            g.drawOval(x, y, ring, ring);
            g.drawOval(x + 1, y + 1, ring - 2, ring - 2); // second ring = thicker line
        }
    }
}

