package com.own.cyberpunk.repository;

import com.own.cyberpunk.domain.Gun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GunRepository extends JpaRepository<Gun, Long> {
}
