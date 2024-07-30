package com.own.cyberpunk.service.serviceImpl;

import com.own.cyberpunk.domain.Fighter;
import com.own.cyberpunk.domain.Gun;
import com.own.cyberpunk.dto.GunDto;
import com.own.cyberpunk.dto.GunForm;
import com.own.cyberpunk.repository.FighterRepository;
import com.own.cyberpunk.repository.GunRepository;
import com.own.cyberpunk.service.GunService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GunServiceImpl implements GunService {

    private final GunRepository gunRepository;
    private final ModelMapper modelMapper;
    private final FighterRepository fighterRepository;

    public GunServiceImpl(GunRepository gunRepository, ModelMapper modelMapper, FighterRepository fighterRepository) {
        this.gunRepository = gunRepository;
        this.modelMapper = modelMapper;
        this.fighterRepository = fighterRepository;
    }

    @Override
    public List<GunDto> getGuns() {
        return gunRepository.findAll().stream()
                .map(gun -> modelMapper.map(gun, GunDto.class))
                .toList();
    }

    @Override
    public void addGun(GunForm gunForm) {
        Gun gun = modelMapper.map(gunForm, Gun.class);
        gunRepository.save(gun);
    }

    @Override
    public void deleteGun(Long id) {
        gunRepository.delete(gunRepository.findById(id).orElse(null));
    }

    @Override
    public void assignGun(Long fighterId, Long gunId) {
        Gun gun = gunRepository.findById(gunId).orElse(null);
        Fighter fighter = fighterRepository.findById(fighterId).orElse(null);

        gun.setFighter(fighter);
        gunRepository.save(gun);
    }
}
