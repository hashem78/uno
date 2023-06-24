package org.hashem.uno.engine.rule;

import com.google.common.collect.Iterables;
import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.rule.StateDependantRule;
import org.hashem.uno.engine.state.State;

public final class DifferentValueRule extends StateDependantRule {

    public DifferentValueRule(State state, Card card) {
        super(state, card);
    }

    @Override
    public boolean apply() {
        return Iterables.getLast(state.playPile().cards()).value() != card.value();
    }

    @Override
    public String toString() {
        return "DifferentValueRule";
    }
}
