package org.hashem.uno.engine.rule;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RecordBuilder
public record RuleSet(
        List<Rule> rules,
        boolean startWIth,
        Function2 strategy) implements RuleSetBuilder.With, Rule {

    public RuleSet() {
        this(new ArrayList<>(), true, (p, c) -> p && c.apply());
    }
    public RuleSet(boolean startWIth, Function2 strategy) {
        this(new ArrayList<>(), startWIth, strategy);
    }

    public RuleSet add(Rule rule) {
        var rulesCopy = new ArrayList<>(rules);
        rulesCopy.add(rule);
        return this.withRules(rulesCopy);
    }

    @Override
    public boolean apply() {

        var result = startWIth;

        for (var rule : rules) {
            result = strategy.apply(result, rule);
        }

        return result;
    }
}