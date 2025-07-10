package com.pellipandiri.userservice.repository;


import com.pellipandiri.userservice.entities.FunctionHalls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FunctionHallsRepository extends JpaRepository<FunctionHalls,Long> {

    Optional<FunctionHalls> findByFunctionHallsName(String functionHallName);

}
