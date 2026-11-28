package apps.checkers;

import Styles.GridStyle;
import Styles.Style;
import game.GamePanel;
import apps.IApp;
import game.Piece;
import primitive.Text;
import vector.Vector2;
import vector.Vector3;

import javax.swing.*;
import java.awt.*;

public class Checkers implements IApp {
    private static final Style CHECKERS_STYLE = Style.builder()
            .fill(new Color(44, 30, 35)) // Game Backgorund
            .outline(new Color(157, 95, 57)) // Game Outline
            .build();

    private final GamePanel gamePanel = new GamePanel(600, 600, CHECKERS_STYLE);

    private Board board;


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

        // Place pieces on board
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 != 0) {
                    if (r < 3) {
                        board.placePiece(new CheckerPiece(true), c, r);
                    } else if (r >= 5) {
                        board.placePiece(new CheckerPiece(false), c, r);
                    }
                }
            }
        }

        gamePanel.include(board, title);
    }

    protected class CheckerPiece extends Piece {
        public static final Style RED = Style.builder()
                .fill(new Color(190, 32, 32))
                .outline(new Color(70, 9, 9))
                .build();
        public static final Style BLACK = Style.builder()
                .fill(new Color(16, 15, 15))
                .outline(new Color(25, 25, 30))
                .build();

        private boolean isRed;

        public CheckerPiece(boolean isRed) {
            super(24, isRed ? RED : BLACK);
            this.isRed = isRed;
        }

        @Override
        public boolean canMove(int deltaX, int deltaY) {
            if (deltaX == 0 && deltaY == 0) {return true;}
            int direction = isRed ? -1 : 1;

            return Math.abs(deltaX) == 1 && deltaY == direction;
        }
    }

    @Override
    public JPanel getJPanel() {
        return gamePanel;
    }

    @Override
    public void update() {

    }

}
