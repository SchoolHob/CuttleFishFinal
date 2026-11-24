package apps;

import Styles.GridStyle;
import Styles.Style;
import game.Board;
import game.GamePanel;
import game.IGame;
import game.Piece;
import primitive.Text;
import vector.Vector2;
import vector.Vector3;

import javax.swing.*;
import java.awt.*;

public class Checkers implements IGame {
    private static final Style CHECKERS_STYLE = Style.builder()
            .fill(new Color(44, 30, 35)) // Game Backgorund
            .outline(new Color(157, 95, 57)) // Game Outline
            .build();

    private final GamePanel gamePanel = new GamePanel(600, 600, CHECKERS_STYLE);

    private Board board;
    private Style p1Style, p2Style;


    public Checkers() {
        Text title = new Text("Checkers", new Vector2(345, 100), 25, CHECKERS_STYLE.getOutline());

        board = new Board(
                new Vector3(250, 150),
                35,
                GridStyle.builder()
                        .fill(new Color(225, 50, 50))
                        .secondaryFill(new Color(63, 39, 23))
                        .outline(Color.BLACK)
                        .build());

        p1Style = Style.builder()
                .fill(new Color(190, 32, 32))
                .outline(new Color(70, 9, 9))
                .build();
        p2Style = Style.builder()
                .fill(new Color(16, 15, 15))
                .outline(new Color(25, 25, 30))
                .build();

        // Place pieces on board
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 != 0) {
                    if (r < 3) {
                        board.placePiece(new Piece(24, p1Style), c, r);
                    } else if (r >= 5) {
                        board.placePiece(new Piece(24, p2Style), c, r);
                    }
                }
            }
        }

        gamePanel.include(board, title);
    }

    @Override
    public void update() {

    }

    @Override
    public JPanel getJPanel() {
        return gamePanel;
    }
}
