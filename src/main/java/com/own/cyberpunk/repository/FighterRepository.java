package com.own.cyberpunk.repository;

import com.own.cyberpunk.domain.Fighter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FighterRepository extends CrudRepository<Fighter, Long> {
}
