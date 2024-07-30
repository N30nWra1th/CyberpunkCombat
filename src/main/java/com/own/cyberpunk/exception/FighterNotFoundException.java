package com.own.cyberpunk.exception;

public class FighterNotFoundException extends CyberpunkCombatException{

        public FighterNotFoundException(Long id) {
            super(id);
        }
}
