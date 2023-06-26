package org.hashem.uno.game.events;

import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.event.Event;

public record CardChosenEvent(Card card) implements Event {

}
