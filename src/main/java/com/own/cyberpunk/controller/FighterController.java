package com.own.cyberpunk.controller;

import com.own.cyberpunk.dto.FighterDto;
import com.own.cyberpunk.dto.MeleeDto;
import com.own.cyberpunk.dto.ShootingDto;
import com.own.cyberpunk.service.FighterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void createCharacter(@RequestBody FighterDto fighterDto) {
        log.info("New fighter has been created successfully.");
        fighterService.createCharacter(fighterDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FighterDto> getCharacters() {
        try {
            log.info("Characters have been retrieved successfully.");
            return fighterService.getAllCharacters();

        } catch (Exception e) {
            log.error("No characters in database.");
            return null;
        }

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FighterDto getCharacter(@PathVariable("id") Long id) {
        try {
            log.info("Character with id " + id + " has been retrieved successfully.");
            return fighterService.getCharacter(id);
        } catch (Exception e) {
            log.error("Character with id " + id + " not found.");
            return null;
        }
    }

    @PostMapping("/shoot")
    @ResponseStatus(HttpStatus.OK)
    public String shoot(@RequestBody ShootingDto shootingDto){
        return fighterService.shoot(shootingDto);
    }

//    @PostMapping("/melee")
//    @ResponseStatus(HttpStatus.OK)
//    public String melee(@RequestBody MeleeDto meleeDto){
//        return fighterService.meleeAttack(meleeDto);
//    }

    @PostMapping("/damage/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String damage(@PathVariable("id") Long id){
        return fighterService.damageRoll(id);
    }
}
