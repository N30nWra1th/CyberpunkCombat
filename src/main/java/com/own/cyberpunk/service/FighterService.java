package com.own.cyberpunk.service;

import com.own.cyberpunk.dto.FighterDto;

import java.util.List;

public interface FighterService {
    void createCharacter(FighterDto fighterDto);

    List<FighterDto> getAllCharacters();

    FighterDto getCharacter(Long id);
}
