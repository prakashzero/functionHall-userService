package com.pellipandiri.userservice.repository;

import com.pellipandiri.userservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,String> {

    List<Address> findAllByCity(String city);
}
