package com.pellipandiri.userservice.repository;

import com.pellipandiri.userservice.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, String> {
    Optional<Owner> findByEmail(String email);
    Optional<Owner> findByGstNumber(String gstNumber);
    Optional<Owner> findByPanNumber(String panNumber);
    boolean existsByEmail(String email);
    boolean existsByGstNumber(String gstNumber);
    boolean existsByPanNumber(String panNumber);
} 