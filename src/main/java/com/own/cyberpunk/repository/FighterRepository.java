package com.own.cyberpunk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FighterRepository extends CrudRepository<Character, Long> {
}
