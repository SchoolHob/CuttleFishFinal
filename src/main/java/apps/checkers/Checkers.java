package apps.checkers;

import Styles.GridStyle;
import Styles.Style;
import game.GamePanel;
import apps.IApp;
import game.IGame;
import game.Piece;
import game.Player;
import primitive.Text;
import vector.Vector2;
import vector.Vector3;

import javax.swing.*;
import java.awt.*;

public class Checkers implements IApp, IGame {
    private static final Style CHECKERS_STYLE = Style.builder()
            .fill(new Color(44, 30, 35)) // Game Backgorund
            .outline(new Color(157, 95, 57)) // Game Outline
            .build();

    private final GamePanel gamePanel = new GamePanel(600, 600, CHECKERS_STYLE);

    private CheckersBoard board;
    private Player player1, player2;
    private boolean isPlayerOneTurn;

    //Game Setup
    public Checkers() {
        initializePlayers();

        Text title = new Text("Checkers", new Vector2(345, 100), 25, CHECKERS_STYLE.getOutline());

        board = new CheckersBoard( this,
                new Vector3(250, 150),
                35,
                GridStyle.builder()
                        .fill(new Color(225, 50, 50))
                        .secondaryFill(new Color(63, 39, 23))
                        .outline(Color.BLACK)
                        .build());

        // Place pieces on board
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 != 0) {
                    if (r < 3) { //Creates player 2 as the top 3 rows
                        board.placePiece(new CheckersPiece(player2, true), c, r);
                    } else if (r >= 5) {
                        board.placePiece(new CheckersPiece(player1, false), c, r);
                    }
                }
            }
        }

        gamePanel.include(board, title, player1, player2);
    }

    private void initializePlayers(){
        player1 = new Player("Black", Style.builder()
                .fill(CheckersPiece.BLACK.getFill())
                .brightOutline()
                .build());
        player2 = new Player("Red", Style.builder()
                .fill(CheckersPiece.RED.getFill())
                .brightOutline()
                .build());

        player1.setIconPosition(Vector3.at(15, 15));
        player2.setIconPosition(Vector3.at(15, 45));
        player1.setIconSize(20);
        player2.setIconSize(20);

        isPlayerOneTurn = true; // Black moves first
        player1.startTurn();
        player1.setFocus(true);
        player2.setFocus(false);
    }


    //IApp methods
    @Override
    public JPanel getJPanel() {
        return gamePanel;
    }

    @Override
    public void update() {}



    //IGame methods
    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }

    @Override
    public Player getCurrentPlayer() {
        return isPlayerOneTurn ? player1 : player2;
    }

    @Override
    public void nextTurn() {
        isPlayerOneTurn = !isPlayerOneTurn;

        if(isPlayerOneTurn){
            player2.endTurn();
            player1.startTurn();
        }
        else{
            player1.endTurn();
            player2.startTurn();
        }

        player1.setFocus(isPlayerOneTurn);
        player2.setFocus(!isPlayerOneTurn);
    }
}
