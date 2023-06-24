package org.hashem.uno.engine.rule;

import org.hashem.uno.engine.action.NoEffectAction;
import org.hashem.uno.engine.card.Card;

public final class HasActionRule extends StateInDependantRule {

    public HasActionRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return !(card.action() instanceof NoEffectAction) &&
                !(new NotWildCardRule(card).apply());
    }

    @Override
    public String toString() {
        return "HasActionRule";
    }
}
