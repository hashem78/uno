package org.hashem.uno.engine.rule;

import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.state.State;

import java.util.Objects;

public abstract class StateDependantRule implements Rule {
    protected final State state;
    protected final Card card;

    public StateDependantRule(State state, Card card) {
        this.state = state;
        this.card = card;
    }

    public State state() {
        return state;
    }

    public Card card() {
        return card;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (StateDependantRule) obj;
        return Objects.equals(this.state, that.state) &&
                Objects.equals(this.card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, card);
    }

    @Override
    public String toString() {
        return "StateDependantRule[" +
                "state=" + state + ", " +
                "card=" + card + ']';
    }

}
