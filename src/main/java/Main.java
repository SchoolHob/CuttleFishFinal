

import apps.checkers.Checkers;
import apps.Launcher;

import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());

    void main() {
        System.setProperty(
                "java.util.logging.SimpleFormatter.format",
                "[%4$s] %5$s%n"
        );

        Launcher.launchGame(new Checkers());
    }
}