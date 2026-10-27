package mancala;

import game.Board;
import game.IGame;
import game.Launcher;
import input.Clickable;
import org.junit.jupiter.api.Test;
import primitive.Drawable;
import primitive.Point;
import primitive.Triangle;
import vector.Vector3;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MancalaTest {
    private Mancala mancala = new Mancala();

    @Test
    public void testMancala() {
        Launcher.launchGame(new Mancala());
        Launcher.closeAll();
        assertEquals(0, Launcher.getWindowCount());
    }

    @Test
    public void testPoint() {
        Point point = new Point(Vector3.ZERO);
        Vector3 delta = new Vector3(10, 10, 10);
        point.move(delta);
        assertEquals(Vector3.ZERO.add(delta), point.position);
    }

    @Test
    public void testTriangle() {
        Triangle triangle = new Triangle(Vector3.ZERO, Vector3.ONE, Vector3.at(0, 1, 0));
        Vector3 delta = new Vector3(10, 10, 10);
        triangle.move(delta);
        assertEquals(Vector3.ZERO.add(delta), triangle.a);
        assertEquals(Vector3.ONE.add(delta), triangle.b);
        assertEquals(Vector3.at(0, 1, 0).add(delta), triangle.c);
    }

    @Test
    public void testBoardIsDrawable(){
        Board board = new Board(Vector3.ZERO, 10);
        assert (board instanceof Drawable);
    }

    @Test
    public void testBoardIsClickable(){
        Board board = new Board(Vector3.ZERO, 10);
        assert (board instanceof Clickable);
    }

    @Test
    public void mancalaIsIGame(){
        assert (mancala instanceof IGame);
    }

}
