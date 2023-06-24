package org.hashem.uno.engine.value;

public final class CardValue extends Value {

    public CardValue(int value) {
        super(value);
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
