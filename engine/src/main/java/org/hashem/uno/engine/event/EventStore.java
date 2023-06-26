package org.hashem.uno.engine.event;

import com.google.common.collect.Iterables;
import org.hashem.uno.engine.state.State;

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

    public <T extends Event> EventStore registerHandler(Class<T> eventType, EventHandler<T> listener) {
        handlers.put(eventType, listener);
        return this;
    }

    public <T extends State> EventStore registerListener(StateListener<T> listener) {
        listeners.add(listener);
        return this;
    }

    public <T extends Event> void addEvent(T event) throws Exception {

        if (!handlers.containsKey(event.getClass())) return;
        previousEvents.add(event);
        @SuppressWarnings("unchecked") var val = (EventHandler<T>) handlers.get(event.getClass());

        var state = val.handle(Iterables.getLast(previousStates), event);
        setState(state);
    }

    public EventStore setState(State state) {
        if (state == Iterables.getLast(previousStates)) return this;
        for (var listener : listeners)
            listener.call(Iterables.getLast(previousStates), state);
        previousStates.add(state);
        return this;
    }

    public State currentState() {
        return Iterables.getLast(previousStates);
    }
}
