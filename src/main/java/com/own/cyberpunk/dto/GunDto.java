package com.own.cyberpunk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GunDto {
    private Long id;
    private String weaponTypes;
    private Integer accuracy;
    private String concealability;
    private String damage;
    private Integer shots;
    private Integer rateOfFire;
    private String reliability;
}
