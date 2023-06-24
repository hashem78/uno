package org.hashem.uno.engine.rule;

import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.state.State;

public final class DifferentActionRule extends StateDependantRule {

    public DifferentActionRule(State state, Card card) {
        super(state, card);
    }

    @Override
    public boolean apply() {
        return !(new SameActionRule(state, card).apply());
    }

    @Override
    public String toString() {
        return "DifferentActionRule";
    }
}
