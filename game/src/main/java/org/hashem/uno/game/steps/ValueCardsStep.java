package org.hashem.uno.game.steps;

import org.hashem.uno.engine.Color;
import org.hashem.uno.engine.action.NoEffectAction;
import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.pipleline.Step;
import org.hashem.uno.engine.value.CardValue;

import java.util.List;

public record ValueCardsStep(Color color) implements Step<List<Card>, List<Card>> {

    @Override
    public List<Card> execute(List<Card> input) {

        for (int i = 0; i <= 9; i++) {
            input.add(new Card(new NoEffectAction(), color, new CardValue(i)));
        }
        for (int i = 1; i <= 9; i++) {
            input.add(new Card(new NoEffectAction(), color, new CardValue(i)));
        }
        return input;
    }
}
