package org.hashem.uno.engine.card;

import org.hashem.uno.engine.action.Action;

import java.util.Objects;

final public class ColorlessActionCard extends ColorlessCard {

    final Action action;

    public ColorlessActionCard(Action action) {

        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColorlessActionCard that = (ColorlessActionCard) o;
        return Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), action);
    }

    @Override
    public String toString() {
        return "ColorlessActionCard{" +
                "action=" + action +
                '}';
    }

    @Override
    public void accept(CardVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String representation() {
        return action.representation();
    }
}
