package com.own.cyberpunk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShootingDto {
    private Long fighterId;
    private Long gunId;
    private String skill;
    private Integer targetRange;
}
