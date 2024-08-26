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
import com.own.cyberpunk.util.DiceReader;
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
        fighter.setAttributes(fighterDto.getAttributes());
        fighter.setSkills(fighterDto.getSkills());
        fighter.setGuns(null);
        fighter.setMeleeWeapons(null);
        fighterRepository.save(fighter);
    }

    @Override
    public void createNpc(FighterDto npcDto, String difficulty) {
//        Fighter npc = modelMapper.map(npcDto, Fighter.class);
        Fighter npc = new Fighter();
        Fighter savedFighter = fighterRepository.save(npc);

        npc.setName("npc" + savedFighter.getId());
        npc.setGuns(null);
        npc.setMeleeWeapons(null);
        int score = 3;
        int addition = 0;
        switch (difficulty) {
            case "Easy":
                addition = 3;
            case "Medium":
                addition = 5;
            case "Hard":
                addition = 7;
            default:
                addition = 10;
        }
        int attSkillPointGen = score + (int) (Math.random() * addition + 1);
//        skill és attribute maphez foreach?

        fighterRepository.save(npc);

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
        Long id = shootingDto.getFighterId();
        Fighter fighter = fighterRepository.findById(id).orElse(null);
        String skill = shootingDto.getSkill();
        Integer targetRange = shootingDto.getTargetRange();
        Gun gun = gunRepository.findById(shootingDto.getGunId()).orElse(null);
        int range = gun.getRange();
        if (fighter.getGuns().isEmpty() || gun == null) {
            throw new RuntimeException("You don't have a gun, choom!");
        }
        try {
            int skillCheck = fighter.getAttributes().get(Attributes.REFLEX) + fighter.getSkills().get(Skills.valueOf(skill)) + DiceRoller.rollDice(1,10,0);
            int difficulty = getDifficultyByGunType(targetRange, range);
            return skillCheck > difficulty ? skillCheck + "! You got'em, choom! You hit'em in their " + getHitArea(DiceRoller.rollNaturalDTen()) : skillCheck + ". Too low... You missed the target, you gonk!";
        } catch (GunNotFoundException e) {
            return "You don't have a gun!";
        }
    }

    @Override
    public String damageRoll(Long id) {
        String dice = gunRepository.findById(id).get().getDamage();
        int damage = DiceReader.diceConverter(dice);
        return "Your weapon did " + damage + " points of damage.";
    }

    @Override
    public int initRoll(Long id) {
        Fighter fighter = fighterRepository.findById(id).orElse(null);
        return fighter.getAttributes().get(Attributes.REFLEX) + fighter.getAttributes().get(Attributes.COMBAT_SENSE) + DiceRoller.rollNaturalDTen();
    }


}
