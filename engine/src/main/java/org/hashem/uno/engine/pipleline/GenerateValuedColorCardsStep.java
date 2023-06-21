package org.hashem.uno.engine.pipleline;

import org.hashem.uno.engine.Color;
import org.hashem.uno.engine.action.DrawTwoAction;
import org.hashem.uno.engine.action.ReverseAction;
import org.hashem.uno.engine.action.SkipAction;
import org.hashem.uno.engine.card.Card;

import java.util.List;

public record GenerateValuedColorCardsStep(Color color) implements Step<List<Card>, List<Card>> {

    @Override
    public List<Card> execute(List<Card> input) {

        for (int i = 0; i < 2; i++) {
            input.add(new Card(new ReverseAction(), color, null));
            input.add(new Card(new SkipAction(), color, null));
            input.add(new Card(new DrawTwoAction(), color, null));
        }
        return input;
    }
}
