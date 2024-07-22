package com.own.cyberpunk.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meleeWeapons")
public class MeleeWeapons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer accuracy;

    @Column
    private String damage;

    @ManyToOne
    @JoinColumn(name = "fighter_id")
    private Fighter fighter;
}
