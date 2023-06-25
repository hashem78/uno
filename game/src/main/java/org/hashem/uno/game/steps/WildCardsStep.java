package org.hashem.uno.game.steps;

import org.hashem.uno.engine.Color;
import org.hashem.uno.engine.action.WildAction;
import org.hashem.uno.engine.action.WildDrawFourAction;
import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.pipleline.Step;
import org.hashem.uno.engine.value.EmptyCardValue;

import java.util.List;

public record WildCardsStep() implements Step<List<Card>, List<Card>> {

    @Override
    public List<Card> execute(List<Card> input) {

        for (int i = 0; i < 4; i++)
            input.add(new Card(new WildAction(), Color.NoColor, new EmptyCardValue()));
        for (int i = 0; i < 4; i++)
            input.add(new Card(new WildDrawFourAction(), Color.NoColor, new EmptyCardValue()));

        return input;
    }
}
