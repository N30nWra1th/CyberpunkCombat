package com.own.cyberpunk.controller;

import com.own.cyberpunk.dto.GunDto;
import com.own.cyberpunk.service.GunService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

}
