package org.hashem.uno.engine.action;

import org.hashem.uno.engine.state.State;

final public class ReverseAction implements Action {

    @Override
    public String representation() {
        return "Reverse";
    }
    @Override
    public State accept(ActionVisitor visitor) {
        return visitor.visit(this);
    }
}
