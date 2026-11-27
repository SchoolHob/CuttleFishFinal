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

    private IMancala gameState;
    private Vector3 position;
    private int width, height;
    private int pitSize;
    private static final int MARGIN = 10;
    public static final int P1_STORE_INDEX = 6;
    public static final int P2_STORE_INDEX = 13;

    private Circle[] pitsAndStores = new Circle[14];
    private GridStyle boardStyle;

    private ArrayList<Piece>[] marbles = new ArrayList[14];
    private ArrayList<Piece> marblesInHand;
    private int nextPitIndex;


    public MancalaBoard(IMancala game, Vector3 position, int size, GridStyle boardStyle) {
        this.gameState = game;
        this.pitSize = size;
        this.position = position;
        this.boardStyle = boardStyle;

        initializePitsAndStores();

        this.width = 8 * pitSize + 9 * MARGIN;
        this.height = 2 * pitSize + 2 * MARGIN*2;

        this.marblesInHand = new ArrayList<>();
        this.nextPitIndex = -1;


        MouseHandler.getInstance().addClickable(this);
    }

    public void placePiece(Piece piece, int index) {
        if (index < 0 || index > P2_STORE_INDEX) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        marbles[index].add(piece);
        if (index == P1_STORE_INDEX) {
            gameState.getPlayer1().givePoints(1);
        } else if (index == P2_STORE_INDEX) {
            gameState.getPlayer2().givePoints(1);
        }

        piece.setDrawPosition(
                Vector3.ZERO
                        .weightedRandomOffset((pitsAndStores[index].getSize().x - piece.getSize()) / 2.0, 1.0)
                        .toVector2()
                        .add(piece.getCenterOffest()));


    }

    public int getP1StoreCount() {
        return marbles[P1_STORE_INDEX].size();
    }
    public int getP2StoreCount() {
        return marbles[P2_STORE_INDEX].size();
    }

    public ArrayList<Piece> pickupMarblesFromPit(int index) {
        if (!isPit(index)) {
            logger.log(Level.WARNING, "Cannnot pickup from a store");
            return new ArrayList<>();
        }
        ArrayList<Piece> marblesFromPit = marbles[index];
        marbles[index] = new ArrayList<>();
        return marblesFromPit;
    }

    public Piece dropNextMarbleFromHand() {
        Piece marble = marblesInHand.getLast();
        marblesInHand.removeLast();
        return marble;
    }

    public boolean selectPit(int index){

        if (marblesInHand.isEmpty()) {
            if (isPitOnCurrentPlayersSide(index)) {
                // Pick up all marbles from pit
                marblesInHand = pickupMarblesFromPit(index);
                nextPitIndex = index;
                incrementNextPitIndex();
                return true;
            }

            logger.log(Level.WARNING, "Pit " + index + " selected, cannot pickup from a store or opponent side.");
            return false;
        }

        // Marbles ARE in hand:
        if (index != nextPitIndex) {
            if (index == nextPitIndex-1 && marbles[index].isEmpty()) {
                // Cancel move (Go again, select another pit)
                int handSize = marblesInHand.size();
                for (int i = 0; i < handSize; i++) {
                    marbles[index].add(dropNextMarbleFromHand());
                }

                return true;
            }
            logger.log(Level.WARNING, "Pit " + index + " selected, cannot drop in non sequential order, the next pit was: " + nextPitIndex);
            return false;
        }
        if (isOpposingPlayersStore(index)) {
            logger.log(Level.WARNING, "Pit " + index + " selected, cannot drop marbles in other players store.");
            return false;
        }

        if (marblesInHand.size() == 1) {
            if (isCurrentPlayersStore(index)) {
                // EXTRA TURN
                placePiece(dropNextMarbleFromHand(), index);
                return true;
            }
            if (isPitOnCurrentPlayersSide(index) && marbles[index].isEmpty() && !marbles[getOppositePitIndex(index)].isEmpty()) {
                // Capture

                int oppositePitIndex = getOppositePitIndex(index);
                int currentPlayerStoreIndex = getCurrentPlayersStoreIndex();
                for (Piece marble : pickupMarblesFromPit(oppositePitIndex)) {
                    placePiece(marble, currentPlayerStoreIndex);
                }
                placePiece(dropNextMarbleFromHand(), getCurrentPlayersStoreIndex());

                return true;
            }

            placePiece(dropNextMarbleFromHand(), index);
            gameState.nextTurn();
            return true;
        }

        placePiece(dropNextMarbleFromHand(), index);
        incrementNextPitIndex();
        return true;
    }

    private void incrementNextPitIndex() {
        nextPitIndex++;
        if (nextPitIndex >= pitsAndStores.length) {
            nextPitIndex = 0;
        }
        if (isOpposingPlayersStore(nextPitIndex)) {
            incrementNextPitIndex();
        }
    }

    private boolean isOpposingPlayersStore(int index) {
        return (gameState.getCurrentPlayer() == gameState.getPlayer1() && index == P2_STORE_INDEX) || (gameState.getCurrentPlayer() == gameState.getPlayer2() && index == P1_STORE_INDEX);
    }
    private boolean isCurrentPlayersStore(int index) {
        return (gameState.getCurrentPlayer() == gameState.getPlayer1() && index == P1_STORE_INDEX) || (gameState.getCurrentPlayer() == gameState.getPlayer2() && index == P2_STORE_INDEX);
    }
    private int getCurrentPlayersStoreIndex() {
        if (gameState.getCurrentPlayer() == gameState.getPlayer1() ) {
            return P1_STORE_INDEX;
        }
        if (gameState.getCurrentPlayer() == gameState.getPlayer2() ) {
            return P2_STORE_INDEX;
        }
        // Something went wrong with gameState if we get here
        return -1;
    }

    private boolean isPitOnCurrentPlayersSide(int index){
        if (gameState.getCurrentPlayer() == gameState.getPlayer1()) {
            return index >= 0 && index < P1_STORE_INDEX;
        } else if (gameState.getCurrentPlayer() == gameState.getPlayer2()) {
            return index > P1_STORE_INDEX && index < P2_STORE_INDEX;
        }
        return false;
    }

    private int getOppositePitIndex(int index){
        return 12 - index;
    }

    private boolean isPit(int index){
        return index != P1_STORE_INDEX && index != P2_STORE_INDEX;
    }

    private void initializePitsAndStores() {
        double x = position.x + pitSize * 1.5 + MARGIN*2;
        double y = position.y + pitSize + MARGIN*2;

        for (int i = 0; i < 14; i++) {
            marbles[i] = new ArrayList<>();
            if (i < P1_STORE_INDEX) {
                pitsAndStores[i] = new Circle(Vector3.at(x + i*pitSize + i* MARGIN, y + pitSize/2.0 + MARGIN /2.0), pitSize, pitSize)
                        .getStyledCircle(Style.builder().fill(boardStyle.getFill()).outline(boardStyle.getOutline()).build());
            } else if (i == P1_STORE_INDEX) {
                pitsAndStores[i] = new Circle(Vector3.at(x + i*pitSize + i* MARGIN, y), pitSize, pitSize*2 + MARGIN *2)
                        .getStyledCircle(Style.builder().fill(boardStyle.getFill()).outline(gameState.getPlayer1().getStyle().getOutline().darker().darker()).build());
            } else if (i < P2_STORE_INDEX) {
                pitsAndStores[i] = new Circle(Vector3.at(x + (P2_STORE_INDEX-i-1)*pitSize + (P2_STORE_INDEX-i-1)* MARGIN, y - pitSize/2.0 - MARGIN /2.0), pitSize, pitSize)
                        .getStyledCircle(Style.builder().fill(boardStyle.getFill()).outline(boardStyle.getOutline()).build());
            } else if (i == P2_STORE_INDEX) {
                pitsAndStores[i] = new Circle(Vector3.at(x - pitSize - MARGIN, y), pitSize, pitSize * 2 + MARGIN * 2)
                        .getStyledCircle(Style.builder().fill(boardStyle.getFill()).outline(gameState.getPlayer2().getStyle().getOutline().darker().darker()).build());
            }
        }
    }

    @Override
    public void onClick(float mouseX, float mouseY) {
        if (gameState.getCurrentPlayer().canInteract()) {
            for (int i = 0; i < 14; i++) {
                if (pitsAndStores[i].contains(mouseX, mouseY)) {
                    selectPit(i);
                    logger.log(Level.INFO, "Pit index: " + i);
                    break;
                }
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
