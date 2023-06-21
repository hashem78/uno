package org.hashem.uno.engine.event;

import org.hashem.uno.engine.state.State;

public interface EventHandler<T extends Event> {
    State handle(State currentState, T event);
}
