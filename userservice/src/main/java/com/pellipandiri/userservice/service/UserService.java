package com.pellipandiri.userservice.service;


import com.pellipandiri.userservice.ExceptionHandler.FunctionNameNotFound;
import com.pellipandiri.userservice.ExceptionHandler.UserRequestException;
import com.pellipandiri.userservice.entities.Address;
import com.pellipandiri.userservice.entities.FunctionHalls;
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


    public List<FunctionHalls> getFunctionHalls(UserRequest userRequest) throws UserRequestException,FunctionNameNotFound {
        if(userRequest==null){
            throw new UserRequestException("UserRequest is empty");
        }
        if(userRequest.getFunctionName() == null){
            // search based on the latitude
            // returing all the function halls win future need to send based on the radius

            return functionalHallsRepository.findAll();
        }else if(userRequest.getCity()!=null){
            return addressRepository
                    .findAllByCity(userRequest.getCity().toLowerCase()).
                    stream().map(Address::getFunctionHalls).collect(Collectors.toList());
        }else{
            return List.of(functionalHallsRepository.
                    findByFunctionHallsName(userRequest.getFunctionName()).
                    orElseThrow(()->new FunctionNameNotFound("Function Name not Found")));
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
