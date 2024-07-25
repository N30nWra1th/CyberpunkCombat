package com.own.cyberpunk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShootingDto {
    Long id;
    String skill;
    Integer targetRange;
}
