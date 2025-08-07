package com.pellipandiri.userservice.repository;

import com.pellipandiri.userservice.entities.FunctionHalls;
import com.pellipandiri.userservice.entities.FunctionHallsImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionHallImagesRepository extends JpaRepository<FunctionHallsImages,Long> {

    List<FunctionHallsImages> findAllByFunctionId(FunctionHalls functionId);

}
