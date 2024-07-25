package com.own.cyberpunk.service.serviceImpl;

import com.own.cyberpunk.domain.MeleeWeapons;
import com.own.cyberpunk.dto.MeleeForm;
import com.own.cyberpunk.repository.MeleeRepository;
import com.own.cyberpunk.service.MeleeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MeleeServiceImpl implements MeleeService {

    private final MeleeRepository meleeRepository;
    private final ModelMapper modelMapper;

    public MeleeServiceImpl(MeleeRepository meleeRepository, ModelMapper modelMapper) {
        this.meleeRepository = meleeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addMelee(MeleeForm meleeForm) {
        MeleeWeapons melee = modelMapper.map(meleeForm, MeleeWeapons.class);
        meleeRepository.save(melee);
    }
}
