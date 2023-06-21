package org.hashem.uno.engine.structures;

import io.soabase.recordbuilder.core.RecordBuilder;
import org.hashem.uno.engine.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RecordBuilder
public record Decks(List<Deck> decks) implements DecksBuilder.With {
    public Decks() {
        this(new ArrayList<Deck>());
    }

    public int indexOf(Deck deck) {
        return decks.indexOf(deck);
    }

    public Decks remove(int index) {
        List<Deck> updated = new ArrayList<>(decks);
        updated.remove(index);
        return this.withDecks(updated);
    }

    public Deck get(int index) {
        return decks.get(index);
    }

    public Decks add(int index, Deck deck) {
        List<Deck> updated = new ArrayList<>(decks);
        updated.add(index, deck);
        return this.withDecks(updated);
    }

    public Decks remove(Card card) {
        List<Deck> updated = new ArrayList<>(decks);
        int indexOfDeckToRemove = -1;

        for (int i = 0; i < decks.size(); i++) {
            if (decks.get(i).contains(card)) {
                indexOfDeckToRemove = i;
                break;
            }
        }

        if (indexOfDeckToRemove == -1)
            return this;
        else {
            var newDeck = updated.remove(indexOfDeckToRemove).remove(card);
            updated.add(indexOfDeckToRemove, newDeck);
            return this.withDecks(updated);
        }
    }

    public Stream<Deck> stream() {
        return decks.stream();
    }
}
