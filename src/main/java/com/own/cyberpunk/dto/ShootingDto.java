package com.own.cyberpunk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShootingDto {
    Long fighterId;
    Long gunId;
    String skill;
    Integer targetRange;
}
