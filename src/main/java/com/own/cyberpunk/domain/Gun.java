package com.own.cyberpunk.domain;

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

    @Column
    private String concealability;

    @Column
    private String damage;

    @Column
    private Integer shots;

    @Column
    private Integer rateOfFire;

    @Column
    private String reliability;

    @Column
    private Integer range;

    @ManyToOne
    @JoinColumn(name = "fighter_id")
    private Fighter fighter;
}
