package org.hashem.uno.engine.rule;

import org.hashem.uno.engine.card.Card;

public final class NotWildCardRule extends StateInDependantRule {

    public NotWildCardRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return !(new WildCardRule(card).apply());
    }

    @Override
    public String toString() {
        return "NotWildCardRule";
    }
}
