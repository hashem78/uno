package org.hashem.uno.engine.action;

import org.hashem.uno.engine.state.State;

final public class SkipAction implements Action {
    @Override
    public String representation() {
        return "Skip";
    }
    @Override
    public State accept(ActionStateVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SkipAction;
    }
}

