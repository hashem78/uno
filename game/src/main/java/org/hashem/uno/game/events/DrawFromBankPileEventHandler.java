package org.hashem.uno.game.events;

import org.hashem.uno.game.events.DrawFromBankPileEvent;
import org.hashem.uno.engine.event.EventHandler;
import org.hashem.uno.engine.state.State;

public final class DrawFromBankPileEventHandler implements EventHandler<DrawFromBankPileEvent> {

    @Override
    public State handle(State state, DrawFromBankPileEvent event) throws Exception {
        try {
            var random = state.bankPile().getRandom(1);

            var modifiedBankPile = random.first();
            var randomCards = random.second();

            var temp = state.decks().remove(state.currentPlayer());
            var modifiedDecks = temp.first();
            var nextPlayerDeck = temp.second();
            var modifiedNextPlayerDeck = nextPlayerDeck.add(randomCards);
            modifiedDecks = modifiedDecks.add(state.currentPlayer(), modifiedNextPlayerDeck);

            return state
                    .withBankPile(modifiedBankPile)
                    .withDecks(modifiedDecks)
                    .withNextPlayer();
        } catch (Exception e) {
            System.out.println(e.toString());
            return state;
        }
    }
}
