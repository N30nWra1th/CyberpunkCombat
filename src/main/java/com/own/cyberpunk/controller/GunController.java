package com.own.cyberpunk.controller;

import com.own.cyberpunk.dto.GunDto;
import com.own.cyberpunk.dto.GunForm;
import com.own.cyberpunk.service.GunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/guns")
public class GunController {
    private final GunService gunService;

    public GunController(GunService gunService) {
        this.gunService = gunService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<GunDto> getGuns() {
        try {
            log.info("Guns have been retrieved successfully.");
            return gunService.getGuns();
        } catch (Exception e) {
            log.error("No guns in database.");
            return null;
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    private void addGun(@RequestBody GunForm gunForm) {
        try {
            gunService.addGun(gunForm);
            log.info("Gun has been added successfully.");
        } catch (Exception e) {
            log.error("Gun could not be added.");
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteGun(@PathVariable Long id) {
        try {
            gunService.deleteGun(id);
            log.info("Gun has been deleted successfully.");
        } catch (Exception e) {
            log.error("Gun could not be deleted.");
        }
    }
}
