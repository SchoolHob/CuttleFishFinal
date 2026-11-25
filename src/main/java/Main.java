

import apps.*;
import game.Launcher;

import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());

    void main() {
        Launcher.launchGame(new Mancala());
    }
}