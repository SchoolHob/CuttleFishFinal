package main.java.game;

import javax.swing.*;

public interface IGame {
    void update();
    default void draw(){
        getJPanel().repaint();
    }
    JPanel getJPanel();
}
