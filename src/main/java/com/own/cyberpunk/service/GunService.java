package com.own.cyberpunk.service;

import com.own.cyberpunk.dto.GunDto;
import com.own.cyberpunk.dto.GunForm;

import java.util.List;

public interface GunService{
    List<GunDto> getGuns();

    void addGun(GunForm gunForm);
}
