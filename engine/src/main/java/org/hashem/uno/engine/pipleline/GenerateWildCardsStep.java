package org.hashem.uno.engine.pipleline;

import org.hashem.uno.engine.action.WildAction;
import org.hashem.uno.engine.action.WildDrawFourAction;
import org.hashem.uno.engine.card.Card;

import java.util.List;

public record GenerateWildCardsStep() implements Step<List<Card>, List<Card>> {

    @Override
    public List<Card> execute(List<Card> input) {

        for (int i = 0; i < 4; i++)
            input.add(new Card(new WildAction(), null, null));
        for (int i = 0; i < 4; i++)
            input.add(new Card(new WildDrawFourAction(), null, null));

        return input;
    }
}
