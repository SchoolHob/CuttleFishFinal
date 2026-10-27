package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClickHandler {
    List<Clickable> clickables;
    private static ClickHandler instance; // Potentionlly this could be a private static list of ClickHandlers and getInstance could return a specific instance based of "layers" so like one ClickHanlder for UI, one for gameplay, ect.
    private static final Logger logger = Logger.getLogger(ClickHandler.class.getName());


    private ClickHandler() {
        clickables = new ArrayList<>();
    }

    public static ClickHandler getInstance() {
        if (instance == null) {
            instance = new ClickHandler();
        }
        return  instance;
    }

    public void addClickable(Clickable clickable) {
        clickables.add(clickable);
    }

    public void addClickables(Clickable ... clickables) {
        for (Clickable clickable : clickables) {
            addClickable(clickable);
        }
    }

    public void removeClickable(Clickable clickable) {
        clickables.remove(clickable);
    }
    public void clearClickables() {
        clickables.clear();
    }

    private void notifyClickables(float mouseX, float mouseY) {
        for (Clickable clickable : clickables) {
            clickable.onClick(mouseX, mouseY);
        }
    }

    public void includePanel(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {// here
                float x = e.getX();
                float y = e.getY();

                notifyClickables(x, y);
                logger.log(Level.INFO, "Click: " + x + " " + y);
            }
        });
    }
}
