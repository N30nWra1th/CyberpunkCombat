package com.own.cyberpunk.service.serviceImpl;

import com.own.cyberpunk.dto.MeleeForm;
import com.own.cyberpunk.repository.MeleeRepository;
import com.own.cyberpunk.service.MeleeService;
import org.springframework.stereotype.Service;

@Service
public class MeleeServiceImpl implements MeleeService {

    private final MeleeRepository meleeRepository;

    public MeleeServiceImpl(MeleeRepository meleeRepository) {
        this.meleeRepository = meleeRepository;
    }

    @Override
    public void addMelee(MeleeForm meleeForm) {

    }
}
