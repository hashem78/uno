package org.hashem.uno.engine.card;

public interface CardVisitor {

    void visit(ColoredActionCard card);

    void visit(ColoredValuedCard card);

    void visit(ColorlessActionCard card);
}

