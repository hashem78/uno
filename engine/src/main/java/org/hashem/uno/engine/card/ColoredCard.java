package org.hashem.uno.engine.card;

import org.hashem.uno.engine.Color;

import java.util.Objects;

public abstract class ColoredCard implements Card {
    protected final Color color;

    public ColoredCard(Color color) {
        this.color = color;
    }

    public Color color() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ColoredCard) obj;
        return Objects.equals(this.color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return "ColoredCard[" +
                "color=" + color + ']';
    }
}

