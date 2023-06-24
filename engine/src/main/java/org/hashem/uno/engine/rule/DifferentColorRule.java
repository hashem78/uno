package org.hashem.uno.engine.rule;

import com.google.common.collect.Iterables;
import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.rule.StateDependantRule;
import org.hashem.uno.engine.state.State;

public final class DifferentColorRule extends StateDependantRule {
    public DifferentColorRule(State state, Card card) {
        super(state, card);
    }

    @Override
    public boolean apply() {
        return Iterables.getLast(state.playPile().cards()).color() != card.color();
    }

    @Override
    public String toString() {
        return "DifferentColorRule";
    }
}
