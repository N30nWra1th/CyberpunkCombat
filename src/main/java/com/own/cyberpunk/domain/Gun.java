package com.own.cyberpunk.domain;

import com.own.cyberpunk.enumeration.Concealability;
import com.own.cyberpunk.enumeration.Reliability;
import com.own.cyberpunk.enumeration.WeaponTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guns")
public class Gun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private WeaponTypes weaponTypes;

    @Column
    private Integer accuracy;

    @Enumerated(EnumType.STRING)
    private Concealability concealability;

    @Column
    private String damage;

    @Column
    private Integer shots;

    @Column
    private Integer rateOfFire;

    @Enumerated(EnumType.STRING)
    private Reliability reliability;

    @Column
    private Integer range;

    @ManyToOne
    @JoinColumn(name = "fighter_id")
    private Fighter fighter;
}
