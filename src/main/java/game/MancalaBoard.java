package game;

import apps.Mancala;
import vector.Vector3;

import java.util.ArrayList;

public class MancalaBoard extends Board {
    ArrayList<Piece> marbles;

    public MancalaBoard(Vector3 position, int size) {
        super(position, size);
    }
}
