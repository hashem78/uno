package org.hashem.uno.engine.event;

import org.hashem.uno.engine.state.State;

public interface StateListener<T extends State> {
    void call(State oldState, State newState);
}
