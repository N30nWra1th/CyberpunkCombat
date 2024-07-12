package com.own.cyberpunk.dto;

import com.own.cyberpunk.enumeration.Attributes;
import com.own.cyberpunk.enumeration.Skills;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;

import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FighterDto {
    @NotFound
    private Long id;
    @NotFound
    private String name;
    @NotFound
    private String role;
    @NotFound
    private Map<Attributes, Integer> attributes;
    @NotFound
    private Map<Skills, Integer> skills;
}
