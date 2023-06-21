package org.hashem.uno.engine.event;

import org.hashem.uno.engine.action.Action;
import org.hashem.uno.engine.card.Card;

public record ActionCardChosenEvent(Action action, Card card) implements Event {

}
