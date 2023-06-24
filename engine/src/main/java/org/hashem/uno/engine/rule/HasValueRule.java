package org.hashem.uno.engine.rule;

import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.rule.StateInDependantRule;
import org.hashem.uno.engine.value.EmptyCardValue;

public final class HasValueRule extends StateInDependantRule {

    public HasValueRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return !(card.value() instanceof EmptyCardValue);
    }

    @Override
    public String toString() {
        return "HasValueRule";
    }
}
