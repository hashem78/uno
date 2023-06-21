package org.hashem.uno.engine.pipleline;

import org.hashem.uno.engine.Color;
import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.state.State;
import org.hashem.uno.engine.state.StateBuilder;
import org.hashem.uno.engine.structures.Pile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BuildBankStep implements Step<State, State> {

    @Override
    public State execute(State currentState) {

        final var bank = new Pipeline<List<Card>, List<Card>>()
                .add(new GenerateValuedColorCardsStep(Color.Red))
                .add(new GenerateValuedColorCardsStep(Color.Green))
                .add(new GenerateValuedColorCardsStep(Color.Blue))
                .add(new GenerateValuedColorCardsStep(Color.Yellow))
                .add(new GenerateColoredValuedActionCardsStep(Color.Red))
                .add(new GenerateColoredValuedActionCardsStep(Color.Green))
                .add(new GenerateColoredValuedActionCardsStep(Color.Blue))
                .add(new GenerateColoredValuedActionCardsStep(Color.Yellow))
                .add(new GenerateWildCardsStep())
                .run(new ArrayList<>());
        Collections.shuffle(bank);
        return currentState.withBankPile(new Pile(bank));
    }
}
