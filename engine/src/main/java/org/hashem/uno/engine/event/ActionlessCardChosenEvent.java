package org.hashem.uno.engine.event;

import org.hashem.uno.engine.card.Card;

public record ActionlessCardChosenEvent(Card card) implements Event {

}
