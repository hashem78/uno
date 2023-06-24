package org.hashem.uno.engine.event;

import org.hashem.uno.engine.card.Card;

public record CardChosenEvent(Card card) implements Event {

}
