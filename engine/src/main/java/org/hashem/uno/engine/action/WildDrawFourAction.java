package org.hashem.uno.engine.action;

import org.hashem.uno.engine.state.State;

final public class WildDrawFourAction implements Action {
    @Override
    public String representation() {
        return "WildDrawFour";
    }

    @Override
    public State accept(ActionStateVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
