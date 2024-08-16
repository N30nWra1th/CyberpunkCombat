package com.own.cyberpunk.service;

import com.own.cyberpunk.dto.FighterDto;
import com.own.cyberpunk.dto.ShootingDto;

import java.util.List;

public interface FighterService {
    void createCharacter(FighterDto fighterDto);

    List<FighterDto> getAllCharacters();

    FighterDto getCharacter(Long id);

    String shoot(ShootingDto shootingDto);

    String damageRoll(Long id);
}
