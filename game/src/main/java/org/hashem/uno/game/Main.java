package org.hashem.uno.game;

import com.google.common.collect.Iterables;
import org.hashem.uno.engine.Color;
import org.hashem.uno.engine.action.DefaultActionVisitor;
import org.hashem.uno.engine.action.NoEffectAction;
import org.hashem.uno.engine.card.Card;
import org.hashem.uno.engine.event.CardChosenEvent;
import org.hashem.uno.engine.render.ConsoleGameRenderer;
import org.hashem.uno.engine.render.Renderer;
import org.hashem.uno.engine.event.EndGameEvent;
import org.hashem.uno.engine.event.EventStore;
import org.hashem.uno.engine.pipleline.*;
import org.hashem.uno.engine.pipleline.PickPlayerCountStep;
import org.hashem.uno.engine.rule.RuleSet;
import org.hashem.uno.engine.rule.StateDependantRule;
import org.hashem.uno.engine.rule.StateInDependantRule;
import org.hashem.uno.engine.state.State;
import org.hashem.uno.engine.structures.Deck;
import org.hashem.uno.engine.structures.Pile;
import org.hashem.uno.engine.value.EmptyCardValue;

import java.util.*;
import java.util.stream.Stream;

final class SameColorRule extends StateDependantRule {

    public SameColorRule(State state, Card card) {
        super(state, card);
    }

    @Override
    public boolean apply() {

        return Iterables.getLast(state.playPile().cards()).color().equals(card.color());
    }

    @Override
    public String toString() {
        return "SameColorRule";
    }
}

final class DifferentColorRule extends StateDependantRule {
    public DifferentColorRule(State state, Card card) {
        super(state, card);
    }

    @Override
    public boolean apply() {
        return Iterables.getLast(state.playPile().cards()).color() != card.color();
    }

    @Override
    public String toString() {
        return "DifferentColorRule";
    }
}

final class SameValueRule extends StateDependantRule {

    public SameValueRule(State state, Card card) {
        super(state, card);
    }

    @Override
    public boolean apply() {
        return Iterables.getLast(state.playPile().cards()).value().equals(card.value());
    }

    @Override
    public String toString() {
        return "SameValueRule";
    }
}

final class DifferentValueRule extends StateDependantRule {

    public DifferentValueRule(State state, Card card) {
        super(state, card);
    }

    @Override
    public boolean apply() {
        return Iterables.getLast(state.playPile().cards()).value() != card.value();
    }

    @Override
    public String toString() {
        return "DifferentValueRule";
    }
}

final class NoEffectActionRule extends StateInDependantRule {

    public NoEffectActionRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return card.action() instanceof NoEffectAction;
    }
}

final class NoColorRule extends StateInDependantRule {

    public NoColorRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return card.color().equals(Color.NoColor);
    }

    @Override
    public String toString() {
        return "NoEffectActionRule";
    }
}

final class WildCardRule extends StateInDependantRule {

    public WildCardRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return new RuleSet()
                .add(new NoColorRule(card))
                .add(new HasNoValueRule(card))
                .apply();
    }
}

final class NotWildCardRule extends StateInDependantRule {

    public NotWildCardRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return !(new WildCardRule(card).apply());
    }

    @Override
    public String toString() {
        return "NotWildCardRule";
    }
}

final class HasActionRule extends StateInDependantRule {

    public HasActionRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return !(card.action() instanceof NoEffectAction);
    }

    @Override
    public String toString() {
        return "HasActionRule";
    }
}


final class HasNoActionRule extends StateInDependantRule {

    public HasNoActionRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return !(new HasActionRule(card).apply());
    }

    @Override
    public String toString() {
        return "HasNoActionRule";
    }
}

final class HasValueRule extends StateInDependantRule {

    public HasValueRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return !(card.value() instanceof EmptyCardValue);
    }

    @Override
    public String toString() {
        return "HasValueRule";
    }
}


final class HasNoValueRule extends StateInDependantRule {

    public HasNoValueRule(Card card) {
        super(card);
    }

    @Override
    public boolean apply() {
        return !(new HasValueRule(card).apply());
    }

    @Override
    public String toString() {
        return "HasNoValueRule";
    }
}


final class SameActionRule extends StateDependantRule {

    public SameActionRule(State state, Card card) {
        super(state, card);
    }

    @Override
    public boolean apply() {

        return Iterables.getLast(state.playPile().cards()).action().equals(card.action());
    }

    @Override
    public String toString() {
        return "SameActionRule";
    }
}

final class DifferentActionRule extends StateDependantRule {

    public DifferentActionRule(State state, Card card) {
        super(state, card);
    }

    @Override
    public boolean apply() {
        return !(new SameActionRule(state, card).apply());
    }

    @Override
    public String toString() {
        return "DifferentActionRule";
    }
}

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

            eventStore.addEvent(new CardChosenEvent(chosenCard));
        }
    }
}