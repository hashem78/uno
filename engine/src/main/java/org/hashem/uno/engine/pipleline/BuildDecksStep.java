package org.hashem.uno.engine.pipleline;

import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.state.State;
import org.hashem.uno.engine.state.StateBuilder;
import org.hashem.uno.engine.structures.Deck;
import org.hashem.uno.engine.structures.Decks;

import java.util.ArrayList;
import java.util.List;

public class BuildDecksStep implements Step<State, State> {
    @Override
    public State execute(State input) {

        List<Deck> decks = new ArrayList<>();
        for (int i = 0; i < input.playerCount(); i++) {
            var deck = input
                    .bankPile().cards().stream()
                    .limit(7)
                    .toList();
            input.bankPile().cards().removeAll(deck);
            decks.add(new Deck(deck));
        }

        return input.withDecks(new Decks(decks));
    }
}
