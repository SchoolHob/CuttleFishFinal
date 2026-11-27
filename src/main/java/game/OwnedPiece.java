package game;

import Styles.Style;

public class OwnedPiece extends Piece{

    private Player owner;

    public OwnedPiece(int size, Style style, Player owner) {
        super(size, style);
    }

    public OwnedPiece(int size, Player owner){
        super(size);
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
