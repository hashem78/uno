package org.hashem.uno.game;

import org.hashem.uno.engine.action.DefaultActionVisitor;
import org.hashem.uno.engine.event.CardChosenEvent;
import org.hashem.uno.engine.event.DrawFromBankPileEvent;
import org.hashem.uno.engine.event.EndGameEvent;
import org.hashem.uno.engine.event.EventStore;
import org.hashem.uno.engine.pipleline.*;
import org.hashem.uno.engine.render.ConsoleGameRenderer;
import org.hashem.uno.engine.render.Renderer;
import org.hashem.uno.engine.rule.*;
import org.hashem.uno.engine.state.State;
import org.hashem.uno.engine.structures.Deck;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        final EventStore eventStore = new EventStore();
        final Renderer renderer = new ConsoleGameRenderer();

        var initialState = new Pipeline<State, State>()
                .add(new PickPlayerCountStep())
                .add(new BuildBankStep())
                .add(new BuildDecksStep())
                .add(new BuildPlayPileStep())
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
                CardChosenEvent.class,
                (state, event) -> {

                    var ruleSet = new RuleSet("All rules", false, (p, c) -> c.apply() || p)
                            .add(new RuleSet("Value card on top of value card, same value & color")
                                    .add(new HasValueRule(event.card()))
                                    .add(new HasValueRule(state.topOfPlayPile()))
                                    .add(new SameValueRule(state, event.card()))
                                    .add(new SameColorRule(state, event.card())))
                            .add(new RuleSet("Value card on top of value card, different value & same color")
                                    .add(new HasValueRule(event.card()))
                                    .add(new HasValueRule(state.topOfPlayPile()))
                                    .add(new DifferentValueRule(state, event.card()))
                                    .add(new SameColorRule(state, event.card())))
                            .add(new RuleSet("Value card on top of value card, same value & different color")
                                    .add(new HasValueRule(event.card()))
                                    .add(new HasValueRule(state.topOfPlayPile()))
                                    .add(new SameValueRule(state, event.card()))
                                    .add(new DifferentColorRule(state, event.card())))
                            .add(new RuleSet("Value card on top of action card")
                                    .add(new HasValueRule(event.card()))
                                    .add(new HasActionRule(state.topOfPlayPile()))
                                    .add(new SameColorRule(state, event.card())))
                            .add(new RuleSet("Value card on top of wild card")
                                    .add(new HasValueRule(event.card()))
                                    .add(new WildCardRule(state.topOfPlayPile())))
                            .add(new RuleSet("Action card on top of Value card")
                                    .add(new HasActionRule(event.card()))
                                    .add(new HasValueRule(state.topOfPlayPile())))
                            .add(new RuleSet("Action card on top of Wild card")
                                    .add(new HasActionRule(event.card()))
                                    .add(new WildCardRule(state.topOfPlayPile())))
                            .add(new RuleSet("Action card on top of action card, same action & color")
                                    .add(new HasActionRule(event.card()))
                                    .add(new HasActionRule(state.topOfPlayPile()))
                                    .add(new SameActionRule(state, event.card()))
                                    .add(new SameColorRule(state, event.card())))
                            .add(new RuleSet("Action card on top of action card, same action & different color")
                                    .add(new HasActionRule(event.card()))
                                    .add(new HasActionRule(state.topOfPlayPile()))
                                    .add(new SameActionRule(state, event.card()))
                                    .add(new DifferentColorRule(state, event.card())))
                            .add(new RuleSet("Action card on top of action card, different action & same color")
                                    .add(new HasActionRule(event.card()))
                                    .add(new HasActionRule(state.topOfPlayPile()))
                                    .add(new DifferentActionRule(state, event.card()))
                                    .add(new SameColorRule(state, event.card())))
                            .add(new RuleSet("Wild card on top of anything")
                                    .add(new WildCardRule(event.card())));

                    if (!ruleSet.apply()) {
                        System.out.println("Failed to apply rule");
                        return state;
                    }

                    var stateAfterAction = event.card()
                            .action()
                            .accept(new DefaultActionVisitor(state));

                    return stateAfterAction
                            .withPlayPile(event.card())
                            .withDecks(stateAfterAction.decks().remove(event.card()));
                }
        );

        eventStore.registerHandler(
                DrawFromBankPileEvent.class,
                (state, event) -> {
                    try {
                        var random = state.bankPile().getRandom(1);

                        var modifiedBankPile = random.first();
                        var randomCards = random.second();

                        var temp = state.decks().remove(state.nextPlayer());
                        var modifiedDecks = temp.first();
                        var nextPlayerDeck = temp.second();
                        var modifiedNextPlayerDeck = nextPlayerDeck.add(randomCards);
                        modifiedDecks = modifiedDecks.add(state.nextPlayer(), modifiedNextPlayerDeck);

                        return state
                                .withBankPile(modifiedBankPile)
                                .withDecks(modifiedDecks)
                                .withNextPlayer();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        eventStore.addEvent(new EndGameEvent());
                        return state;
                    }
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