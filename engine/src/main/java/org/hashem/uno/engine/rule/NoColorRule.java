package org.hashem.uno.engine.rule;

import org.hashem.uno.engine.Color;
import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.rule.StateInDependantRule;

public final class NoColorRule extends StateInDependantRule {

    public NoColorRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return card.color().equals(Color.NoColor);
    }

    @Override
    public String toString() {
        return "NoEffectActionRule";
    }
}
