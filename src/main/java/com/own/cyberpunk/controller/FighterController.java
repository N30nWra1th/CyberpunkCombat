package com.own.cyberpunk.controller;

import com.own.cyberpunk.dto.FighterDto;
import com.own.cyberpunk.service.FighterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/characters")
public class FighterController {
    private final FighterService fighterService;

    public FighterController(FighterService fighterService) {
        this.fighterService = fighterService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCharacter(FighterDto fighterDto){
        log.info("New fighter has been created successfully.");
        fighterService.createCharacter(fighterDto);
    }

}
