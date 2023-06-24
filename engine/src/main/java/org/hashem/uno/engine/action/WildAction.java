package org.hashem.uno.engine.action;

import org.hashem.uno.engine.state.State;

final public class WildAction implements Action {

    @Override
    public String representation() {
        return "Wild";
    }
    @Override
    public State accept(ActionStateVisitor visitor) {
        return visitor.visit(this);
    }
}

