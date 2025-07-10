package com.pellipandiri.userservice.service;


import com.pellipandiri.userservice.entities.FunctionHalls;
import com.pellipandiri.userservice.model.UserRequest;
import com.pellipandiri.userservice.repository.FunctionHallsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private FunctionHallsRepository functionalHallsRepository;

    public UserService(FunctionHallsRepository functionalHallsRepository){
        this.functionalHallsRepository=functionalHallsRepository;
    }


    public List<FunctionHalls> getFunctionHalls(UserRequest userRequest) throws Exception {
        if(userRequest==null){
            throw new Exception("UserRequest is empty");
        }
        if(userRequest.getFunctionName() == null){
            // search based on the latitude
            // returing all the function halls win future need to send based on the radius

            return functionalHallsRepository.findAll();
        }else{
            return List.of(functionalHallsRepository.
                    findByFunctionHallsName(userRequest.getFunctionName()).orElse(null));
        }


    }
}
