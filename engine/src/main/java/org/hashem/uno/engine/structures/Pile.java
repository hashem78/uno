package org.hashem.uno.engine.structures;

import io.soabase.recordbuilder.core.RecordBuilder;
import org.hashem.uno.engine.card.Card;

import java.util.ArrayList;
import java.util.List;

@RecordBuilder
public record Pile(List<Card> cards) implements PileBuilder.With {
    public Pile() {
        this(new ArrayList<Card>());
    }
}
