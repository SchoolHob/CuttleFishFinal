package game;

import Styles.GridStyle;
import Styles.Style;
import input.Clickable;
import input.MouseHandler;
import primitive.Circle;
import primitive.Drawable;
import vector.Vector2;
import vector.Vector3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MancalaBoard implements Drawable, Clickable {
    static Logger logger = Logger.getLogger(MancalaBoard.class.getName());

    private Vector3 position;
    private int width, height;
    private int pitSize;
    private static final int MARGIN = 10;
    public static final int P1_STORE_INDEX = 6;
    public static final int P2_STORE_INDEX = 13;

    private Circle[] pitsAndStores = new Circle[14];
    private GridStyle boardStyle;

    private ArrayList<Piece>[] marbles = new ArrayList[14];
    private ArrayList<Piece> marblesInHand = new ArrayList<>();


    public MancalaBoard(Vector3 position, int size, GridStyle boardStyle) {
        pitSize = size;
        this.position = position;
        this.boardStyle = boardStyle;

        double x = position.x + pitSize * 1.5 + MARGIN*2;
        double y = position.y + pitSize + MARGIN*2;

        for (int i = 0; i < 14; i++) {
            marbles[i] = new ArrayList<>();
            if (i < P1_STORE_INDEX) {
                pitsAndStores[i] = new Circle(Vector3.at(x + i*pitSize + i* MARGIN, y - pitSize/2.0 - MARGIN /2.0), pitSize, pitSize);
            } else if (i == P1_STORE_INDEX) {
                pitsAndStores[i] = new Circle(Vector3.at(x + i*pitSize + i* MARGIN, y), pitSize, pitSize*2 + MARGIN *2);
            } else if (i < P2_STORE_INDEX) {
                pitsAndStores[i] = new Circle(Vector3.at(x + (P2_STORE_INDEX-i-1)*pitSize + (P2_STORE_INDEX-i-1)* MARGIN, y + pitSize/2.0 + MARGIN /2.0), pitSize, pitSize);
            } else if (i == P2_STORE_INDEX) {
                pitsAndStores[i] = new Circle(Vector3.at(x - pitSize - MARGIN, y), pitSize, pitSize * 2 + MARGIN * 2);
            }

            pitsAndStores[i] = pitsAndStores[i].getStyledCircle(Style.builder().fill(boardStyle.getFill()).outline(boardStyle.getOutline()).build());
        }

        width = 8 * pitSize + 9 * MARGIN;
        height = 2 * pitSize + 2 * MARGIN*2;

        MouseHandler.getInstance().addClickable(this);
    }

    public void placePiece(Piece piece, int index) {
        if (index < 0 || index > P2_STORE_INDEX) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        piece.setDrawPosition(
                Vector3.ZERO
                        .weightedRandomOffset((pitsAndStores[index].getSize().x - piece.getSize()) / 2.0, 1.0)
                        .toVector2()
                        .add(piece.getCenterOffest()));
        marbles[index].add(piece);

    }

    public int getP1StoreCount() {
        return marbles[P1_STORE_INDEX].size();
    }
    public int getP2StoreCount() {
        return marbles[P2_STORE_INDEX].size();
    }

    public ArrayList<Piece> pickUpFromPit(int index) {
//        if (index == P1_STORE_INDEX || index == P2_STORE_INDEX) {
//            throw new IndexOutOfBoundsException("Invalid index: " + index + " is a player store.");
//        }
        ArrayList<Piece> marblesFromPit = marbles[index];
        marbles[index] = new ArrayList<>();
        return marblesFromPit;
    }

    public Piece dropNextMarbleFromHand() {
        Piece marble = marblesInHand.getLast();
        marblesInHand.remove(marble);
        return marble;
    }

    public void pitClicked(int index){

        if (marblesInHand.isEmpty()) {
            marblesInHand = pickUpFromPit(index);
        } else {
            placePiece(dropNextMarbleFromHand(), index);
        }

        logger.log(Level.INFO, "Pit " + index + " clicked");
    }

    @Override
    public void onClick(float mouseX, float mouseY) {
        for (int i = 0; i < 14; i++) {
            if (pitsAndStores[i].contains(mouseX, mouseY)) {
                pitClicked(i);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        boardStyle.secondaryFill(g);
        g.fillRoundRect(position.intx(), position.inty(), width, height, MARGIN, MARGIN);
        boardStyle.outline(g);
        g.drawRoundRect(position.intx(), position.inty(), width, height, MARGIN, MARGIN);

        for (int i = 0; i < 14; i++) {
            pitsAndStores[i].draw(g);
            for (Piece marble : marbles[i]) {
                marble.drawWithOffset(g, pitsAndStores[i].getPosition().toVector2());
            }

        }

        for (Piece marble : marblesInHand) {
            marble.drawWithOffset(g, MouseHandler.getInstance().getMousePosition());
        }
    }
}
