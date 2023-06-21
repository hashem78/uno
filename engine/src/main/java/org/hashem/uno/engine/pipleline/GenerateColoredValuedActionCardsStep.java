package org.hashem.uno.engine.pipleline;

import org.hashem.uno.engine.Color;
import org.hashem.uno.engine.card.Card;

import java.util.List;

public record GenerateColoredValuedActionCardsStep(Color color) implements Step<List<Card>, List<Card>> {

    @Override
    public List<Card> execute(List<Card> input) {

        for (int i = 0; i <= 9; i++) {
            input.add(new Card(null,color, i));
        }
        for (int i = 1; i <= 9; i++) {
            input.add(new Card(null,color, i));
        }
        return input;
    }
}
