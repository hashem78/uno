package org.hashem.uno.engine.state;

import io.soabase.recordbuilder.core.RecordBuilder;
import org.hashem.uno.engine.structures.Deck;
import org.hashem.uno.engine.structures.Decks;
import org.hashem.uno.engine.structures.Pile;

@RecordBuilder
public record State(
        int playerCount,
        Pile bankPile,
        Decks decks,
        Pile playPile,
        int currentPlayer,
        int direction) implements StateBuilder.With {

    public State() {

        this(0, new Pile(), new Decks(), new Pile(), 0, 1);
    }

    public int nextPlayer() {
        return Math.floorMod(currentPlayer + direction, playerCount);
    }

    public State withNextPlayer() {
        return this.withCurrentPlayer(nextPlayer());
    }

    public State withReversedDirection() {
        return this.withDirection(direction == 1 ? -1 : 1);
    }

    public Deck getDeck(int index) {
        return decks.get(index);
    }
}
