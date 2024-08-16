package com.own.cyberpunk.util;

public class DiceReader {

    public static int diceConverter(String dice) {
        String[] diceElements = dice.split("[d+]");
        int numberOfDice = 1;
        int diceSides = 0;
        int modifier = 0;

        if (diceElements.length == 1) {
            diceSides = Integer.parseInt(diceElements[0]);
        } else if (diceElements.length == 2) {
            numberOfDice = Integer.parseInt(diceElements[0]);
            diceSides = Integer.parseInt(diceElements[1]);
        } else if (diceElements.length == 3) {
            numberOfDice = Integer.parseInt(diceElements[0]);
            diceSides = Integer.parseInt(diceElements[1]);
            modifier = Integer.parseInt(diceElements[2]);
        } else {
            throw new IllegalArgumentException("Invalid dice format");
        }

        return DiceRoller.rollDice(numberOfDice, diceSides, modifier);
    }
}
