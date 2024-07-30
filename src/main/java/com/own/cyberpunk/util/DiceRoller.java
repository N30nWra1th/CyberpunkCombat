package com.own.cyberpunk.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class DiceRoller {

    public static int rollDice(int diceSides) {
        int result = (int) (Math.random() * diceSides) + 1;

        if (diceSides == 10 && result == 10) {
            log.info("Very nice, it's a ten, rolling again and adding the result");
            result += rollDice(10);
        }else if(diceSides == 10 && result == 1){
            log.error("Critical failure, choom!");
            return 1;
        }
        return result;
    }

    public static int rollNaturalDTen(){
        return (int) (Math.random() * 10 + 1);
    }
}
