package com.own.cyberpunk.domain;

import com.own.cyberpunk.enumeration.Attributes;
import com.own.cyberpunk.enumeration.Skills;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

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

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "fighter_attributes", joinColumns = @JoinColumn(name = "fighter_id"))
    @MapKeyColumn(name = "attribute")
    @MapKeyEnumerated(EnumType.STRING)
    @Column
    private Map<Attributes, Integer> attributes;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "fighter_skills", joinColumns = @JoinColumn(name = "fighter_id"))
    @MapKeyColumn(name = "skill")
    @MapKeyEnumerated(EnumType.STRING)
    @Column
    private Map<Skills, Integer> skills;

    @OneToMany(mappedBy = "fighter")
    private List<Gun> guns;

    @OneToMany(mappedBy = "fighter")
    private List<MeleeWeapons> meleeWeapons;
}
