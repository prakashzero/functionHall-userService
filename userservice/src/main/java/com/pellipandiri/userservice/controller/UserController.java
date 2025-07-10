package com.pellipandiri.userservice.controller;

import com.pellipandiri.userservice.entities.FunctionHalls;
import com.pellipandiri.userservice.model.UserRequest;
import com.pellipandiri.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/functionHalls")
    public ResponseEntity<List<FunctionHalls>> getFunctionHalls(@RequestBody UserRequest userRequest) throws Exception {
        List<FunctionHalls> halls = userService.getFunctionHalls(userRequest);
        return new ResponseEntity<>(halls, HttpStatus.OK);
    }
}
