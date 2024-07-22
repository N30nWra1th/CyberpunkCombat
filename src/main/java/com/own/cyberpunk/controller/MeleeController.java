package com.own.cyberpunk.controller;

import com.own.cyberpunk.service.MeleeService;
import com.own.cyberpunk.dto.MeleeForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/melee")
public class MeleeController {
    private final MeleeService meleeService;

    public MeleeController(MeleeService meleeService) {
        this.meleeService = meleeService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    private void addMelee(@RequestBody MeleeForm meleeForm) {
        try {
            meleeService.addMelee(meleeForm);
            log.info("Melee weapon has been added successfully.");
        } catch (Exception e) {
            log.error("Melee weapon could not be added.");
        }
    }
}
