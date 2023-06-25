package org.hashem.uno.game;

import org.hashem.uno.engine.Game;
import org.hashem.uno.engine.event.CardChosenEvent;
import org.hashem.uno.engine.event.DrawFromBankPileEvent;
import org.hashem.uno.engine.event.EndGameEvent;
import org.hashem.uno.engine.event.EventStore;
import org.hashem.uno.engine.pipleline.*;
import org.hashem.uno.engine.render.Renderer;
import org.hashem.uno.engine.state.State;
import org.hashem.uno.engine.structures.Deck;

import java.util.Scanner;

public class ConsoleGame implements Game {
    @Override
    public void run() {
        final Renderer renderer = new ConsoleGameRenderer();

        var initialState = new Pipeline<State, State>()
                .add(new PickPlayerCountStep())
                .add(new BuildBankStep())
                .add(new BuildDecksStep())
                .add(new BuildPlayPileStep())
                .run(new State());

        final EventStore eventStore = new EventStore()
                .registerHandler(DrawFromBankPileEvent.class, new DrawFromBankPileEventHandler())
                .registerHandler(CardChosenEvent.class, new CardChosenEventHandler())
                .registerHandler(EndGameEvent.class, new EndGameEventHandler())
                .registerListener(new EventStoreListener(renderer))
                .setState(initialState);

        var scanner = new Scanner(System.in);
        while (true) {
            final var currentState = eventStore.currentState();
            if (currentState.decks().stream().anyMatch(Deck::isEmpty)) {
                eventStore.addEvent(new EndGameEvent());
                break;
            }
            System.out.println("To draw a random card from the bank pile enter -1");

            var cardIndex = scanner.nextInt();

            if (cardIndex == -1) {
                eventStore.addEvent(new DrawFromBankPileEvent());
                continue;
            }

            var chosenCard = currentState.decks()
                    .get(currentState.currentPlayer())
                    .get(cardIndex);

            eventStore.addEvent(new CardChosenEvent(chosenCard));
        }
    }
}
