package com.own.cyberpunk.service.serviceImpl;

import com.own.cyberpunk.domain.Fighter;
import com.own.cyberpunk.dto.FighterDto;
import com.own.cyberpunk.repository.FighterRepository;
import com.own.cyberpunk.service.FighterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FighterServiceImpl implements FighterService {
        private final FighterRepository fighterRepository;
        private ModelMapper modelMapper;

    public FighterServiceImpl(FighterRepository fighterRepository, ModelMapper modelMapper){
        this.fighterRepository = fighterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createCharacter(FighterDto fighterDto) {
        Fighter fighter = modelMapper.map(fighterDto, Fighter.class);
        fighter.setGuns(null);
        fighter.setMeleeWeapons(null);
        fighterRepository.save(fighter);
    }
}
