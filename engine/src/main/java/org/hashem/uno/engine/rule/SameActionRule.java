package org.hashem.uno.engine.rule;

import com.google.common.collect.Iterables;
import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.rule.StateDependantRule;
import org.hashem.uno.engine.state.State;

public final class SameActionRule extends StateDependantRule {

    public SameActionRule(State state, Card card) {
        super(state, card);
    }

    @Override
    public boolean apply() {

        return state.topOfPlayPile().action().equals(card.action());
    }

    @Override
    public String toString() {
        return "SameActionRule";
    }
}
