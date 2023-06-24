package org.hashem.uno.engine.structures;

import com.google.common.collect.Iterables;
import io.soabase.recordbuilder.core.RecordBuilder;
import org.hashem.uno.engine.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

@RecordBuilder
public record Pile(List<Card> cards) implements PileBuilder.With {
    public Pile() {
        this(new ArrayList<Card>());
    }

    public Pair<Pile, List<Card>> getRandom(int count) throws Exception {
        if (cards.size() < count)
            throw new Exception("Not enough cards in pile to get " + count + " random cards");

        var removedCards = new ArrayList<Card>();
        var cardsCopy = new ArrayList<>(cards);
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            int randomIndex = rand.nextInt(cardsCopy.size());
            var randomCard = cardsCopy.get(randomIndex);
            removedCards.add(randomCard);
            cardsCopy.remove(randomIndex);
        }
        return new Pair<>(this.withCards(cardsCopy), removedCards);
    }

    public Pair<Pile, List<Card>> getRandom(int count, Function<Card, Boolean> test) throws Exception {
        if (cards.size() < count)
            throw new Exception("Not enough cards in pile to get " + count + " random cards");

        var removedCards = new ArrayList<Card>();
        var cardsCopy = new ArrayList<>(cards);
        Random rand = new Random();

        while (removedCards.size() != count) {
            int randomIndex = rand.nextInt(cardsCopy.size());
            var randomCard = cardsCopy.get(randomIndex);
            if (test.apply(randomCard)) {
                removedCards.add(randomCard);
                cardsCopy.remove(randomIndex);
            }
        }

        return new Pair<>(this.withCards(cardsCopy), removedCards);
    }

    public Pile withCard(Card card) {
        var cardsCopy = new ArrayList<>(cards);
        cardsCopy.add(card);
        return this.withCards(cardsCopy);
    }

    public Card last() {
        return Iterables.getLast(cards);
    }
}
