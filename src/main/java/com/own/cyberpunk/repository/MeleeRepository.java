package com.own.cyberpunk.repository;

import com.own.cyberpunk.domain.MeleeWeapons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeleeRepository extends JpaRepository<MeleeWeapons, Long> {
}
