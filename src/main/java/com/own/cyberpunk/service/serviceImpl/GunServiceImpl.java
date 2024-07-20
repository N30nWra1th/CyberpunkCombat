package com.own.cyberpunk.service.serviceImpl;

import com.own.cyberpunk.domain.Gun;
import com.own.cyberpunk.dto.GunDto;
import com.own.cyberpunk.dto.GunForm;
import com.own.cyberpunk.repository.GunRepository;
import com.own.cyberpunk.service.GunService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GunServiceImpl implements GunService {

    private final GunRepository gunRepository;
    private final ModelMapper modelMapper;

    public GunServiceImpl(GunRepository gunRepository, ModelMapper modelMapper) {
        this.gunRepository = gunRepository;
        this.modelMapper = modelMapper;
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
}
