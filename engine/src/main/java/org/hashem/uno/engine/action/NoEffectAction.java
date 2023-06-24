package org.hashem.uno.engine.action;

import org.hashem.uno.engine.state.State;

public class NoEffectAction implements Action {
    @Override
    public String representation() {
        return "";
    }
    @Override
    public State accept(ActionStateVisitor visitor) {
        return visitor.visit(this);
    }
}
