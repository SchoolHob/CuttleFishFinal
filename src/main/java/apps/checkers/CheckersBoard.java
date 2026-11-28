package apps.checkers;

import Styles.GridStyle;
import game.GridBoard;
import game.IGame;
import game.Player;
import input.MouseHandler;
import primitive.Grid;
import vector.Vector3;

import java.util.List;

public class CheckersBoard extends GridBoard {

    private IGame gameState;

    private Player winner;
    private boolean jumpAvailable = false;

    public CheckersBoard(IGame game, int rows, int cols, Vector3 position, int size, GridStyle gridStyle) {
        super(rows, cols, position, size, gridStyle);
        this.gameState = game;
    }

    public CheckersBoard(IGame game, Vector3 position, int size, GridStyle gridStyle) {
        this(game, DEFAULT_ROWS_COLS.x, DEFAULT_ROWS_COLS.y, position, size, gridStyle);
    }

    //Control game logic
    @Override
    protected void onTileClicked(int x, int y) {
        //Make sure game is over
        if (isGameOver()) {
            return;
        }

        //Try to pick up piece on chose square
        if(pieceInHand == null){
            tryPickUp(x, y);
            return;
        }

        //When playing checkers piece in hand will always be a checkers piece so we can safely cast it
        CheckersPiece piece = (CheckersPiece) pieceInHand;

        int fromX = pieceInHandPos.x;
        int fromY = pieceInHandPos.y;

        //Player tries to put piece back where it was
        if (x == fromX && y == fromY) {
            placePiece(piece, x, y);
            pieceInHand = null;
            return;
        }

        //Check if attemped placement causes a step or a jump. Jumps prioritized
        boolean isJump = isLegalJump(piece, fromX, fromY, x, y);
        boolean isStep = !isJump && isLegalStep(piece, fromX, fromY, x, y);

        //Illegal move chosen, keep holding the piece
        if (!isJump && !isStep) {
            return; // keep holding the piece
        }
        //Step attemped when a jump is available
        if (isStep && jumpAvailable) {
            return;
        }

        // Jump logic
        if (isJump) {
            removePiece((fromX + x) / 2, (fromY + y) / 2); // the jumped piece
            piece.getOwner().givePoints(1);                // show captures next to the player's name
        }

        //Actually move the piece
        placePiece(piece, x, y);
        pieceInHand = null;

        CheckersPiece placedPiece = getPieceAt(x, y);
        int lastRow = placedPiece.getForwardDirection() > 0 ? rows - 1 : 0;
        if (y == lastRow) {
            placedPiece.crownPiece();
        }

        //TODO: Double jump Logic

        jumpAvailable = false;
        Player last_player = gameState.getCurrentPlayer();
        gameState.nextTurn();

        // If the next player can't move (including having no pieces left), the mover wins
        if (!playerHasAnyMove(gameState.getCurrentPlayer())) {
            winner = last_player;
        }

    }

    private boolean isGameOver(){ return winner != null; }

    // void tryPickUp checks to make sure piece is a jump if possible, owned by the right player
    //Picks up piece if the checks are passed
    private void tryPickUp(int x, int y){
        CheckersPiece p = getPieceAt(x, y);
        if (p == null || p.getOwner() != gameState.getCurrentPlayer()) {
            return;
        }

        boolean mustJump =  playerHasAnyJump(p.getOwner());
        boolean canJump = hasJumpFrom(p, x, y);

        if (mustJump && !canJump) {
            return;
        }
        if (!canJump && !hasStepFrom(p, x, y)) {
            return;
        }

        pickUpPiece(x, y);
    }

    //Check all of a player's pieces to see if they can jump
    private boolean playerHasAnyJump(Player player){
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                CheckersPiece p = getPieceAt(x, y);
                if (p != null && p.getOwner() == player && hasJumpFrom(p, x, y)) {
                    jumpAvailable = true;
                    return true;
                }
            }
        }
        return false;
    }

    //Check whether a player can move at all
    private boolean playerHasAnyMove(Player player){
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                CheckersPiece p = getPieceAt(x, y);
                if (p != null && p.getOwner() == player
                        && (hasJumpFrom(p, x, y) || hasStepFrom(p, x, y))) {
                    return true;
                }
            }
        }
        return false;
    }

    //Step move methods
    private boolean hasStepFrom(CheckersPiece piece, int x, int y){
        for (int dx = -1; dx <= 1; dx += 2) {
            for (int dy = -1; dy <= 1; dy += 2) {
                if (isLegalStep(piece, x, y, x + dx, y + dy)) return true;
            }
        }
        return false;
    }

    private boolean isLegalStep(CheckersPiece piece, int fromX, int fromY, int toX, int toY){
        return isOnBoard(toX, toY)
                && getPiece(toX, toY) == null
                && Math.abs(toX - fromX) == 1
                && piece.canMove(toX - fromX, toY - fromY);
    }


    //Jump move methods

    //Check each possible direction a piece can jump and check whether the move is legal
    private boolean hasJumpFrom(CheckersPiece piece, int x, int y) {
        for (int dx = -2; dx <= 2; dx += 4) {
            for (int dy = -2; dy <= 2; dy += 4) {
                if (isLegalJump(piece, x, y, x + dx, y + dy)) return true;
            }
        }
        return false;
    }

    private boolean isLegalJump(CheckersPiece piece, int fromX, int fromY, int toX, int toY){

        //Make sure there are no issues with the physical move of the jump (no pieces in the way, not out, etc)
        if(!isOnBoard(toX, toY) || getPieceAt(toX, toY) != null){ return false; }
        if (Math.abs(toX - fromX) != 2) { return false; }
        //direction
        if (!piece.canMove(toX - fromX, toY - fromY)) { return false; }

        //Ensure you are jumping over one of the opponent's pieces
        CheckersPiece middle = getPieceAt((fromX + toX) / 2, (fromY + toY) / 2);
        return middle != null && middle.getOwner() != piece.getOwner();
    }





    //Check whether a given location is a valid spot on the board
    private boolean isOnBoard(int x, int y){
        return x >= 0 && x < cols && y >= 0 && y < rows;
    }

    //Get the piece at on a given square
    //Return null if space is empty or space are invalid
    private CheckersPiece getPieceAt(int x, int y){
        if (!isOnBoard(x, y)) return null;
        return (getPiece(x, y) instanceof CheckersPiece p) ? p : null;

    }
}
