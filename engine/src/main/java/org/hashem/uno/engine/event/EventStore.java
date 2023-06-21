package org.hashem.uno.engine.event;

import com.google.common.collect.Iterables;
import org.hashem.uno.engine.state.State;
import org.hashem.uno.engine.state.StateBuilder;
import org.hashem.uno.engine.structures.Decks;
import org.hashem.uno.engine.structures.Pile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record EventStore(
        List<Event> previousEvents, List<State> previousStates,
        Map<Class<? extends Event>, EventHandler<? extends Event>> handlers,
        List<StateListener<? extends State>> listeners) {

    public EventStore() {
        this(new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>());
        previousStates.add(new State());
    }

    public <T extends Event> void registerHandler(Class<T> eventType, EventHandler<T> listener) {
        handlers.put(eventType, listener);
    }

    public <T extends State> void registerListener(StateListener<T> listener) {
        listeners.add(listener);
    }

    public <T extends Event> void addEvent(T event) {

        if (!handlers.containsKey(event.getClass())) return;
        previousEvents.add(event);
        @SuppressWarnings("unchecked") var val = (EventHandler<T>) handlers.get(event.getClass());
        try {
            var state = val.handle(Iterables.getLast(previousStates), event);
            setState(state);
        } catch (Exception e) {
            System.out.println(e);
            addEvent(new EndGameEvent());
        }
    }

    public void setState(State state) {
        if (state == Iterables.getLast(previousStates)) return;
        for (var listener : listeners)
            listener.call(Iterables.getLast(previousStates), state);
        previousStates.add(state);
    }

    public State currentState() {
        return Iterables.getLast(previousStates);
    }
}
