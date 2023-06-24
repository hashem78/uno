package org.hashem.uno.engine.action;

import org.hashem.uno.engine.state.State;

public interface ActionStateVisitor {
    State visit(DrawTwoAction action) throws Exception;

    State visit(ReverseAction action);

    State visit(SkipAction action);

    State visit(WildAction action);

    State visit(WildDrawFourAction action) throws Exception;

    State visit(NoEffectAction action);
}