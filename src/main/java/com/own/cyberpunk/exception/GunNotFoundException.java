package com.own.cyberpunk.exception;

public class GunNotFoundException extends CyberpunkCombatException {
    public GunNotFoundException(Long id) {
        super(id);
    }
}
