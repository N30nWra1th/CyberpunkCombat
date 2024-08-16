package com.own.cyberpunk.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class DiceRoller {

    public static int rollDice(int numberOfDice, int diceSides, int modifier) {
        int result = 0;

        for(int i = 0; i < numberOfDice; i++){
            result = (int) ((Math.random() * diceSides) + 1) + modifier;
        }


        if (diceSides == 10 && result == 10) {
            log.info("Very nice, it's a ten, rolling again and adding the result");
            result += rollDice(1,10,0);
        } else if (diceSides == 10 && result == 1) {
            log.error("Critical failure, choom!");
            return 1;
        }
        return result;
    }

    // d10 roller for when 1 is not a critical fail
    public static int rollNaturalDTen() {
        return (int) (Math.random() * 10 + 1);
    }
}
