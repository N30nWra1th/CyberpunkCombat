package com.own.cyberpunk.exception;

import lombok.Getter;

@Getter
public class CyberpunkCombatException extends RuntimeException{
    private Long id;
    private String name;

    public CyberpunkCombatException(Long id) {
        this.id = id;
    }

    public CyberpunkCombatException(String name) {
        this.name = name;
    }
}
