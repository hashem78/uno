package org.hashem.uno.engine.rule;

import org.hashem.uno.engine.card.Card;

import java.util.Objects;

public abstract class StateInDependantRule implements Rule {
    protected final Card card;

    public StateInDependantRule(Card card) {
        this.card = card;
    }

    public Card card() {
        return card;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (StateInDependantRule) obj;
        return Objects.equals(this.card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card);
    }

    @Override
    public String toString() {
        return "StateInDependantRule[" +
                "card=" + card + ']';
    }
}
