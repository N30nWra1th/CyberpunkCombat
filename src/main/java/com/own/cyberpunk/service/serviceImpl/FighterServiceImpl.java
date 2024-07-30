package com.own.cyberpunk.service.serviceImpl;

import com.own.cyberpunk.domain.Fighter;
import com.own.cyberpunk.domain.Gun;
import com.own.cyberpunk.dto.FighterDto;
import com.own.cyberpunk.dto.ShootingDto;
import com.own.cyberpunk.enumeration.Attributes;
import com.own.cyberpunk.enumeration.Skills;
import com.own.cyberpunk.exception.GunNotFoundException;
import com.own.cyberpunk.repository.FighterRepository;
import com.own.cyberpunk.repository.GunRepository;
import com.own.cyberpunk.service.FighterService;
import com.own.cyberpunk.util.DiceRoller;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FighterServiceImpl implements FighterService {
    private final FighterRepository fighterRepository;
    private final ModelMapper modelMapper;
    private final GunRepository gunRepository;

    public FighterServiceImpl(FighterRepository fighterRepository, ModelMapper modelMapper, GunRepository gunRepository) {
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

    private String getHitArea(int rollResult){
        switch (rollResult){
            case 1:
            case 2:
            case 3:
                return "head";
            case 4:
                return "torso";
            case 5:
                return "right arm";
            case 6:
            case 7:
                return "left arm";
            case 8:
            case 9:
                return "right leg";
            case 10:
                return "left leg";
            default:
                return "<rolling error>";
        }
    }
    @Override
    public String shoot(ShootingDto shootingDto) {
        //getting fighter by id for skill and attribute points
        Long id = shootingDto.getId();
        Fighter fighter = fighterRepository.findById(id).orElse(null);
        //getting skill and target range from shootingDto
        String skill = shootingDto.getSkill();
        Integer targetRange = shootingDto.getTargetRange();
        //getting range of the gun
        Gun gun = gunRepository.findById(fighter.getGuns().get(0).getId()).orElse(null);
        if (fighter.getGuns().isEmpty() || gun == null) {
            throw new RuntimeException("You don't have a gun, choom!");
        }

        int range = gun.getRange();

        //calculating skill check and difficulty
        try {
            int skillCheck = fighter.getAttributes().get(Attributes.REFLEX) + fighter.getSkills().get(Skills.valueOf(skill)) + DiceRoller.rollDice(10);
            int difficulty = getDifficultyByGunType(targetRange, range);
            //returning result
            return skillCheck > difficulty ? skillCheck + "! You hit the target, choom! You hit'em in their " + getHitArea(DiceRoller.rollNaturalDTen()) + "!" : skillCheck + ". Too low, you missed the target, you gonk...";
        } catch (GunNotFoundException e) {
            return "You don't have a gun!";
        }
    }
}
