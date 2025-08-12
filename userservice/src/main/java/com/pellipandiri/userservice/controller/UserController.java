package com.pellipandiri.userservice.controller;

import com.pellipandiri.userservice.ExceptionHandler.FunctionNameNotFound;
import com.pellipandiri.userservice.ExceptionHandler.UserRequestException;
import com.pellipandiri.userservice.dto.FunctionHallDTO;
import com.pellipandiri.userservice.entities.FunctionHalls;
import com.pellipandiri.userservice.mapper.FunctionHallMapper;
import com.pellipandiri.userservice.model.UserRequest;
import com.pellipandiri.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/functionHalls")
    public ResponseEntity<List<FunctionHallDTO>> getFunctionHalls(
            @RequestParam(value = "city",required = false) String city,
            @RequestParam(value = "functionHall",required = false) String functionHall,
            @RequestParam(value = "location",required = false) String location)
            throws UserRequestException, FunctionNameNotFound {
        List<FunctionHallDTO> halls = userService.getFunctionHalls(city,functionHall,location);
        List<FunctionHallDTO> response = halls.stream()
                .sorted(Comparator.comparingDouble(FunctionHallDTO::getRating).reversed())
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/functionHall")
    public ResponseEntity<FunctionHallDTO> getFunctionHalls(@RequestParam("id") Long id) throws UserRequestException, FunctionNameNotFound {
        FunctionHalls hall = userService.getFunctionHallById(id);
        FunctionHallDTO response = Stream.of(hall).map(FunctionHallMapper::toDTO).findFirst().get();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
