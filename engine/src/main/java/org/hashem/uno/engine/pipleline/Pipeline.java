package org.hashem.uno.engine.pipleline;

import java.util.ArrayList;
import java.util.List;

public class Pipeline<I, O> {
    final List<Step<I, O>> steps = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public Pipeline<I, O> add(Step<I, O> step) {

        steps.add(step);
        return  this;
    }

    @SuppressWarnings("unchecked")
    public O run(I firstStepInput) {
        Object stepInput = firstStepInput;
        O stepOutput = null;

        for (var step : steps) {
            stepOutput = step.execute((I) stepInput);
            stepInput = stepOutput;
        }

        return stepOutput;
    }
}
