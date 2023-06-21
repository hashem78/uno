package org.hashem.uno.game;

import org.hashem.uno.engine.render.ConsoleGameRenderer;
import org.hashem.uno.engine.render.Renderer;
import org.hashem.uno.engine.event.ActionlessCardChosenEvent;
import org.hashem.uno.engine.event.EndGameEvent;
import org.hashem.uno.engine.event.EventStore;
import org.hashem.uno.engine.pipleline.*;
import org.hashem.uno.engine.pipleline.PickPlayerCountStep;
import org.hashem.uno.engine.state.State;
import org.hashem.uno.engine.structures.Deck;

import java.util.*;


public class Main {
    public static void main(String[] args) {

        final EventStore eventStore = new EventStore();
        final Renderer renderer = new ConsoleGameRenderer();

        var initialState = new Pipeline<State, State>()
                .add(new PickPlayerCountStep())
                .add(new BuildBankStep())
                .add(new BuildDecksStep())
                .run(new State());


        eventStore.registerListener((p, n) -> {
            if (p == n) return;
            renderer.render(n);
        });

        eventStore.registerHandler(
                EndGameEvent.class,
                (s, e) -> {
                    System.out.println("The game has ended!, " + s.currentPlayer() + " has won!");
                    return s;
                }
        );

        eventStore.registerHandler(
                ActionlessCardChosenEvent.class,
                (state, event) -> {

                    return state
                            .withDecks(state.decks().remove(event.card()))
                            .withNextPlayer();
                }
        );

        eventStore.registerHandler(
                ActionlessCardChosenEvent.class,
                (state, event) -> {

                    return state
                            .withDecks(state.decks().remove(event.card()))
                            .withNextPlayer();
                }
        );

        eventStore.setState(initialState);

        var scanner = new Scanner(System.in);
        while (true) {
            final var currentState = eventStore.currentState();
            if (currentState.decks().stream().anyMatch(Deck::isEmpty)) {
                eventStore.addEvent(new EndGameEvent());
                break;
            }

            var cardIndex = scanner.nextInt();
            var chosenCard = currentState.decks()
                    .get(currentState.currentPlayer())
                    .get(cardIndex);

            eventStore.addEvent(new ActionlessCardChosenEvent(currentState.decks().get(currentState.currentPlayer()).get(cardIndex)));
        }
    }
}

interface Dog {
    void bark();
}

interface Jafar extends Dog {
    void eat();
}