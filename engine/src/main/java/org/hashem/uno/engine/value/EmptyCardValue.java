package org.hashem.uno.engine.value;

public final class EmptyCardValue extends Value {

    public EmptyCardValue() {
        super(-1);
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
