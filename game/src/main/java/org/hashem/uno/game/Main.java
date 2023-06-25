package org.hashem.uno.game;

import org.hashem.uno.engine.Game;

public class Main {
    public static void main(String[] args) {

        Game game = new ConsoleGame();
        game.run();
    }
}