package org.hashem.uno.engine.action;

import org.hashem.uno.engine.state.State;

public interface Action {
    String representation();

    State accept(ActionStateVisitor visitor) throws Exception;
}

