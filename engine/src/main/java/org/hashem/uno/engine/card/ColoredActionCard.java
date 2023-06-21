package org.hashem.uno.engine.card;

import org.hashem.uno.engine.action.Action;
import org.hashem.uno.engine.Color;

import java.util.Objects;

final public class ColoredActionCard extends ColoredCard {

    final Action action;

    public ColoredActionCard(Color color, Action action) {

        super(color);
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColoredActionCard that = (ColoredActionCard) o;
        return Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), action);
    }

    @Override
    public String toString() {
        return "ColoredActionCard{" +
                "action=" + action +
                "color=" + color +
                '}';
    }

    @Override
    public String representation() {
        return color.color() + action.representation();
    }
}
