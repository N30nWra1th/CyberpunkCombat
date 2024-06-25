package com.own.cyberpunk.domain;

import com.own.cyberpunk.enumeration.Attributes;
import com.own.cyberpunk.enumeration.Skills;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fighter")
@Builder
public class Fighter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String role;

    @Enumerated(EnumType.STRING)
    private Attributes attributes;

    @Enumerated(EnumType.STRING)
    private Skills skills;

    @OneToMany(mappedBy = "fighter")
    private List<Gun> guns;

    @OneToMany(mappedBy = "fighter")
    private List<MeleeWeapons> meleeWeapons;
}
