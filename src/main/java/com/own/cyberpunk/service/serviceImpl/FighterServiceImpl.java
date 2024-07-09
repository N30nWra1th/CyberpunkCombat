package com.own.cyberpunk.service.serviceImpl;

import com.own.cyberpunk.domain.Fighter;
import com.own.cyberpunk.dto.FighterDto;
import com.own.cyberpunk.repository.FighterRepository;
import com.own.cyberpunk.service.FighterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FighterServiceImpl implements FighterService {
        private final FighterRepository fighterRepository;
        private final ModelMapper modelMapper;

    public FighterServiceImpl(FighterRepository fighterRepository, ModelMapper modelMapper){
        this.fighterRepository = fighterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createCharacter(FighterDto fighterDto) {
        Fighter fighter = modelMapper.map(fighterDto, Fighter.class);
        System.out.println(fighter);
        System.out.println(fighterDto);
        fighter.setAttributes(fighterDto.getAttributes());
        fighter.setSkills(fighterDto.getSkills());
        fighter.setGuns(null);
        fighter.setMeleeWeapons(null);
        fighterRepository.save(fighter);
    }

    @Override
    public List<FighterDto> getAllCharacters() {
        List<Fighter> fighters = (List<Fighter>) fighterRepository.findAll();

        return fighters.stream()
                .map(fighter -> modelMapper.map(fighter, FighterDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FighterDto getCharacter(Long id) {
        Fighter fighter = fighterRepository.findById(id).orElse(null);
        return modelMapper.map(fighter, FighterDto.class);
    }
}
