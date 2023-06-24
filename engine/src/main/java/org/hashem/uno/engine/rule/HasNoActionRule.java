package org.hashem.uno.engine.rule;

import org.hashem.uno.engine.card.Card;

public final class HasNoActionRule extends StateInDependantRule {

    public HasNoActionRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return !(new HasActionRule(card).apply());
    }

    @Override
    public String toString() {
        return "HasNoActionRule";
    }
}
