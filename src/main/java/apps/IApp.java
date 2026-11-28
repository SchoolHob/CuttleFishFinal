package apps;

import javax.swing.*;

public interface IApp {
    void update();
    default void draw(){
        getJPanel().repaint();
    }
    JPanel getJPanel();
}
