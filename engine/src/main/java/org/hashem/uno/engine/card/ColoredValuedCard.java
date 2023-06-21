package org.hashem.uno.engine.card;

import org.hashem.uno.engine.Color;

import java.util.Objects;

final public class ColoredValuedCard extends ColoredCard {

    final int value;

    public ColoredValuedCard(Color color, int value) {

        super(color);
        this.value = value;
    }

    @Override
    public String toString() {
        return "ColoredValuedCard{" +
                "value=" + value +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColoredValuedCard that = (ColoredValuedCard) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), value);
    }

    public int value() {
        return value;
    }

    @Override
    public String representation() {
        return color.color() + value;
    }
}
