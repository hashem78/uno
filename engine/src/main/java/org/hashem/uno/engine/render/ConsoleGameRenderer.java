package org.hashem.uno.engine.render;

import org.hashem.uno.engine.state.State;

public class ConsoleGameRenderer implements Renderer {

    @Override
    public void render(State state) {
        System.out.println("Current Player: " + state.currentPlayer());
        var deck = state.decks().decks().get(state.currentPlayer());
        int counter = 0;

        for (var card : deck.cards()) {
            System.out.println(counter + ") " + card);
            counter++;
        }
    }
}
