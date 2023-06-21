package org.hashem.uno.engine.event;

import org.hashem.uno.engine.card.Card;

public record ActionCardChosenEvent(Card card) implements Event {

}
