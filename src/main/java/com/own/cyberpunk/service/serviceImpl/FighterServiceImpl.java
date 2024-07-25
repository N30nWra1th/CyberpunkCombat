package com.own.cyberpunk.service.serviceImpl;

import com.own.cyberpunk.domain.Fighter;
import com.own.cyberpunk.dto.FighterDto;
import com.own.cyberpunk.dto.ShootingDto;
import com.own.cyberpunk.enumeration.Attributes;
import com.own.cyberpunk.repository.FighterRepository;
import com.own.cyberpunk.repository.GunRepository;
import com.own.cyberpunk.service.FighterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FighterServiceImpl implements FighterService {
        private final FighterRepository fighterRepository;
        private final ModelMapper modelMapper;
    private final GunRepository gunRepository;

    public FighterServiceImpl(FighterRepository fighterRepository, ModelMapper modelMapper, GunRepository gunRepository){
        this.fighterRepository = fighterRepository;
        this.modelMapper = modelMapper;
        this.gunRepository = gunRepository;
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

    private int getDifficultyByGunType(int targetRange, int gunRange) {
        int difficulty;
        if (targetRange <= gunRange / 4) { //close range
            difficulty = 15;
        } else if (targetRange <= gunRange / 2) { //medium range
            difficulty = 20;
        } else if (targetRange < gunRange) { //full range
            difficulty = 25;
        } else { //out of range
            difficulty = 30;
        }
        return difficulty;
    }

    @Override
    public String shoot(ShootingDto shootingDto) {
        Long id = shootingDto.getId();
        String skill = shootingDto.getSkill();
        Integer targetRange = shootingDto.getTargetRange();
        Fighter fighter = fighterRepository.findById(id).orElse(null);
        int range = gunRepository.findById(fighter.getGuns().get(0).getId()).orElse(null).getRange();
        int skillRoll = (int) (Math.random() * 10) + 1;
        int skillCheck = fighter.getAttributes().get(Attributes.REFLEX) + fighter.getSkills().get(skill) + skillRoll;
        int difficulty = getDifficultyByGunType(targetRange, range);
        return skillCheck > difficulty ? "You hit the target!" : "You missed the target!";
    }
}
