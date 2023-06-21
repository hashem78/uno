package org.hashem.uno.engine.card;

import org.hashem.uno.engine.Color;
import org.hashem.uno.engine.action.Action;

public record Card(Action action, Color color) {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (action != null)
            builder.append(action.representation());
        if (color != null)
            builder.append(color.color());
        return builder.toString();
    }
}
