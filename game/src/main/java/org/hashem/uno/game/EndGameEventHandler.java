package org.hashem.uno.game;

import org.hashem.uno.engine.event.EndGameEvent;
import org.hashem.uno.engine.event.EventHandler;
import org.hashem.uno.engine.state.State;

final class EndGameEventHandler implements EventHandler<EndGameEvent> {

    @Override
    public State handle(State state, EndGameEvent event) throws Exception {
        System.out.println("The game has ended!, " + state.currentPlayer() + " has won!");
        return state;
    }
}
