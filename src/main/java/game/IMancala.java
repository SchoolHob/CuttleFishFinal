package game;

public interface IMancala {
    Player getPlayer1();
    Player getPlayer2();
    Player getCurrentPlayer();
    void nextTurn();
}
