package org.hashem.uno.engine.card;

import io.soabase.recordbuilder.core.RecordBuilder;
import org.hashem.uno.engine.Color;
import org.hashem.uno.engine.action.Action;
import org.hashem.uno.engine.value.Value;


@RecordBuilder
public record Card(Action action, Color color, Value value) implements CardBuilder.With {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (action != null)
            builder.append(action.representation());
        if (color != null)
            builder.append(color.color());
        if (value != null)
            builder.append(value);
        return builder.toString();
    }
}
