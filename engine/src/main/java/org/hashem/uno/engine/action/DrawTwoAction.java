package org.hashem.uno.engine.action;

import org.hashem.uno.engine.state.State;

final public class DrawTwoAction implements Action {
    @Override
    public String representation() {
        return "DrawTwo";
    }

    @Override
    public State accept(ActionStateVisitor visitor) throws Exception {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DrawTwoAction;
    }
}

