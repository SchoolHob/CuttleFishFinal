package game;

public interface IGame {
    Player getPlayer1();
    Player getPlayer2();
    Player getCurrentPlayer();
    void nextTurn();
}
