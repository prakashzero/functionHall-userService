package com.pellipandiri.userservice.controller;

import com.pellipandiri.userservice.dto.BookingDTO;
import com.pellipandiri.userservice.dto.FunctionHallCreateDTO;
import com.pellipandiri.userservice.dto.FunctionHallDTO;
import com.pellipandiri.userservice.dto.OwnerDTO;
import com.pellipandiri.userservice.entities.Booking;
import com.pellipandiri.userservice.entities.Owner;
import com.pellipandiri.userservice.ExceptionHandler.BookingException;
import com.pellipandiri.userservice.ExceptionHandler.OwnerException;
import com.pellipandiri.userservice.service.ImageService;
import com.pellipandiri.userservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private ImageService imageService;

    // Owner Management Endpoints
    
    @PostMapping("/create")
    public ResponseEntity<Owner> createOwner(@RequestBody OwnerDTO ownerDTO) {
        try {
            Owner owner = ownerService.createOwner(ownerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(owner);
        } catch (OwnerException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable String id) {
        try {
            OwnerDTO owner = ownerService.getOwnerByIdWithDTO(id);
            return ResponseEntity.ok(owner);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<OwnerDTO> getOwnerByEmail(@PathVariable String email) {
        try {
            OwnerDTO owner = ownerService.getOwnerByEmail(email);
            return ResponseEntity.ok(owner);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    // Function Hall Management Endpoints
    
    @PostMapping("/function-hall/create")
    public ResponseEntity<FunctionHallDTO> createFunctionHall(@RequestBody FunctionHallCreateDTO createDTO) {
        try {
            FunctionHallDTO functionHall = ownerService.createFunctionHall(createDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(functionHall);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/function-halls/{ownerId}")
    public ResponseEntity<List<FunctionHallDTO>> getFunctionHallsByOwner(@PathVariable String ownerId) {
        try {
            List<FunctionHallDTO> functionHalls = ownerService.getFunctionHallsByOwner(ownerId);
            return ResponseEntity.ok(functionHalls);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/function-hall/{id}")
    public ResponseEntity<FunctionHallDTO> getFunctionHallById(@PathVariable Long id) {
        try {
            FunctionHallDTO functionHall = ownerService.getFunctionHallById(id);
            return ResponseEntity.ok(functionHall);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    
    // Booking Management Endpoints
    
    @PostMapping("/booking/create")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            BookingDTO booking = ownerService.createBooking(bookingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        } catch (BookingException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/booking/function-hall/{functionHallId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByFunctionHall(@PathVariable Long functionHallId) {
        try {
            List<BookingDTO> bookings = ownerService.getBookingsByFunctionHall(functionHallId);
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/booking/upcoming/{functionHallId}")
    public ResponseEntity<List<BookingDTO>> getUpcomingBookingsByFunctionHall(@PathVariable Long functionHallId) {
        try {
            List<BookingDTO> bookings = ownerService.getUpcomingBookingsByFunctionHall(functionHallId);
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
    @PutMapping("/booking/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam String status) {
        try {
            Booking.BookingStatus bookingStatus = Booking.BookingStatus.valueOf(status.toUpperCase());
            BookingDTO booking = ownerService.updateBookingStatus(bookingId, bookingStatus);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PostMapping("/functionHall/addImage")
    public ResponseEntity<String> addImageForFunctionHall(
            @RequestParam("functionHallId") Long functionHallId,
            @RequestParam("file") MultipartFile file) {

        try {
            String fileUrl = imageService.addImageToFunctionHall(functionHallId,file);

            return ResponseEntity.status(HttpStatus.CREATED).body(fileUrl);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error Uploading file:" +e.getMessage());
        }

    }

    @GetMapping("/functionHall/Images")
    public ResponseEntity<List<String>> getImages(@RequestParam Long functionHallId) {

        return ResponseEntity.status(HttpStatus.OK).body(imageService.getImagesForFunctionHall(functionHallId));

    }

}