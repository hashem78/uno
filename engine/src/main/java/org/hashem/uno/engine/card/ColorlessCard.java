package org.hashem.uno.engine.card;

public abstract class ColorlessCard implements Card {

    public abstract void accept(CardVisitor visitor);
}


