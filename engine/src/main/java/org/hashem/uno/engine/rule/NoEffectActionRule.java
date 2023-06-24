package org.hashem.uno.engine.rule;

import org.hashem.uno.engine.action.NoEffectAction;
import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.rule.StateInDependantRule;

public final class NoEffectActionRule extends StateInDependantRule {

    public NoEffectActionRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return card.action() instanceof NoEffectAction;
    }
}
