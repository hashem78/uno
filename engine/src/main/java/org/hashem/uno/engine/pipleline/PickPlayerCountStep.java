package org.hashem.uno.engine.pipleline;

import org.hashem.uno.engine.state.State;

import java.util.Scanner;

public class PickPlayerCountStep implements Step<State, State> {

    static int readPlayerCount() {
        var scanner = new Scanner(System.in);
        int playerCount = -1;
        while (playerCount == -1) {

            System.out.print("Enter an integer value (2-10): ");
            if (!scanner.hasNextInt()) {
                scanner.next();
                continue;
            }
            int value = scanner.nextInt();
            if (value <= 1 || value >= 11) continue;
            playerCount = value;
        }
        return playerCount;
    }

    @Override
    public State execute(State currentState) {
        int playerCount = readPlayerCount();
        return currentState.withPlayerCount(playerCount);
    }
}
