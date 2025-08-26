package com.pellipandiri.userservice.service;


import com.pellipandiri.userservice.ExceptionHandler.FunctionNameNotFound;
import com.pellipandiri.userservice.ExceptionHandler.UserRequestException;
import com.pellipandiri.userservice.dto.FunctionHallDTO;
import com.pellipandiri.userservice.entities.Address;
import com.pellipandiri.userservice.entities.FunctionHalls;
import com.pellipandiri.userservice.mapper.FunctionHallMapper;
import com.pellipandiri.userservice.model.UserRequest;
import com.pellipandiri.userservice.repository.AddressRepository;
import com.pellipandiri.userservice.repository.FunctionHallsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private FunctionHallsRepository functionalHallsRepository;

    private AddressRepository addressRepository;


    public UserService(FunctionHallsRepository functionalHallsRepository,AddressRepository addressRepository){
        this.functionalHallsRepository=functionalHallsRepository;
        this.addressRepository = addressRepository;
    }


    public List<FunctionHallDTO> getFunctionHalls(String city, String functionHall, String location) throws UserRequestException,FunctionNameNotFound {
        if(city!=null){
            return addressRepository
                    .findAllByCityContains(city.toLowerCase()).
                    stream().map(Address::getFunctionHalls).map(FunctionHallMapper::toDTO).collect(Collectors.toList());
        }
        else if(functionHall != null){

            return List.of(functionalHallsRepository.
                    findByFunctionHallsName(functionHall.toLowerCase()).
                    orElseThrow(()->new FunctionNameNotFound("Function Name not Found"))).
                    stream().map(FunctionHallMapper::toDTO).toList();
        }else if(location!=null){
            // search based on the latitude
            // returing all the function halls win future need to send based on the radius
                return functionalHallsRepository.findAll().stream().map(FunctionHallMapper::toDTO).toList();
        }else{
            return functionalHallsRepository.findAll().stream().map(FunctionHallMapper::toDTO).toList();
        }


    }

    public FunctionHalls getFunctionHallById(Long id) throws FunctionNameNotFound {
        if(id==null){
            throw new FunctionNameNotFound("Id is null");
        }else{
            return functionalHallsRepository.findById(id).orElseThrow(
                    ()->new FunctionNameNotFound("Function Id is not found")
            );
        }

    }
}
