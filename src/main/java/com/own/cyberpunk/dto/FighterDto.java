package com.own.cyberpunk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FighterDto {
    @NotFound
    private String name;
    @NotFound
    private String role;
    @NotFound
    private String attributes;
    @NotFound
    private String skills;
}
