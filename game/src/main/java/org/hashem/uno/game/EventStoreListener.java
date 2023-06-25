package org.hashem.uno.game;

import org.hashem.uno.engine.event.StateListener;
import org.hashem.uno.engine.render.Renderer;
import org.hashem.uno.engine.state.State;

final class EventStoreListener implements StateListener<State> {
    final Renderer renderer;

    public EventStoreListener(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void call(State oldState, State newState) {
        if (oldState == newState) return;
        renderer.render(newState);
    }
}
