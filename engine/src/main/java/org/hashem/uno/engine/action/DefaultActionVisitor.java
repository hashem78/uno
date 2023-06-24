package org.hashem.uno.engine.action;

import org.hashem.uno.engine.state.State;

public class DefaultActionVisitor implements ActionStateVisitor {

    final State state;

    public DefaultActionVisitor(State state) {
        this.state = state;
    }

    @Override
    public State visit(DrawTwoAction action) throws Exception {

        var random = state.bankPile().getRandom(2);
        var modifiedBankPile = random.first();
        var randomCards = random.second();

        var temp = state.decks().remove(state.nextPlayer());
        var modifiedDecks = temp.first();
        var nextPlayerDeck = temp.second();
        var modifiedNextPlayerDeck = nextPlayerDeck.add(randomCards);
        modifiedDecks = modifiedDecks.add(state.nextPlayer(), modifiedNextPlayerDeck);

        return state
                .withBankPile(modifiedBankPile)
                .withDecks(modifiedDecks)
                .withNextPlayer();
    }

    @Override
    public State visit(ReverseAction action) {

        return state
                .withReversedDirection()
                .withNextPlayer();
    }

    @Override
    public State visit(SkipAction action) {

        return state
                .withNextPlayer()
                .withNextPlayer();
    }

    @Override
    public State visit(WildAction action) {

        return state;
    }

    @Override
    public State visit(WildDrawFourAction action) throws Exception {

        var random = state.bankPile().getRandom(4);
        var modifiedBankPile = random.first();
        var randomCards = random.second();

        var temp = state.decks().remove(state.nextPlayer());
        var modifiedDecks = temp.first();
        var nextPlayerDeck = temp.second();
        var modifiedNextPlayerDeck = nextPlayerDeck.add(randomCards);
        modifiedDecks = modifiedDecks.add(state.nextPlayer(), modifiedNextPlayerDeck);

        return state
                .withBankPile(modifiedBankPile)
                .withDecks(modifiedDecks);
    }

    @Override
    public State visit(NoEffectAction action) {
        return state.withNextPlayer();
    }
}
