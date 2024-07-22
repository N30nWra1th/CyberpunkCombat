package com.own.cyberpunk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeleeForm {
    private String name;

    private Integer accuracy;

    private String damage;

    private Integer fighterId;
}
