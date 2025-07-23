package com.pellipandiri.userservice.repository;

import com.pellipandiri.userservice.entities.Eminities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EminitiesRepository extends JpaRepository<Eminities, Long> {
    Optional<Eminities> findByName(String name);
    boolean existsByName(String name);
}
