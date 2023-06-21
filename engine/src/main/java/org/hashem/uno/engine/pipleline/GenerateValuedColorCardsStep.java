package org.hashem.uno.engine.pipleline;

import org.hashem.uno.engine.Color;
import org.hashem.uno.engine.action.DrawTwoAction;
import org.hashem.uno.engine.action.ReverseAction;
import org.hashem.uno.engine.action.SkipAction;
import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.card.ColoredActionCard;

import java.util.List;

public record GenerateValuedColorCardsStep(Color color) implements Step<List<Card>, List<Card>> {

    @Override
    public List<Card> execute(List<Card> input) {

        for (int i = 0; i < 2; i++) {
            input.add(new ColoredActionCard(color, new ReverseAction()));
            input.add(new ColoredActionCard(color, new SkipAction()));
            input.add(new ColoredActionCard(color, new DrawTwoAction()));
        }
        return input;
    }
}
