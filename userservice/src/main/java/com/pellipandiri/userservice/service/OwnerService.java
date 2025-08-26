package com.pellipandiri.userservice.service;

import com.pellipandiri.userservice.dto.BookingDTO;
import com.pellipandiri.userservice.dto.FunctionHallCreateDTO;
import com.pellipandiri.userservice.dto.FunctionHallDTO;
import com.pellipandiri.userservice.dto.OwnerDTO;
import com.pellipandiri.userservice.entities.*;
import com.pellipandiri.userservice.ExceptionHandler.BookingException;
import com.pellipandiri.userservice.ExceptionHandler.OwnerException;
import com.pellipandiri.userservice.mapper.FunctionHallMapper;
import com.pellipandiri.userservice.mapper.OwnerMapper;
import com.pellipandiri.userservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.pellipandiri.userservice.mapper.BookingMapper;
import org.springframework.web.multipart.MultipartFile;

import static com.pellipandiri.userservice.mapper.FunctionHallMapper.toDTO;

@Service
public class OwnerService {
    
    @Autowired
    private OwnerRepository ownerRepository;
    
    @Autowired
    private FunctionHallsRepository functionHallsRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private EminitiesRepository eminitiesRepository;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    // Owner operations
    @Transactional
    public Owner createOwner(OwnerDTO ownerDTO) {
        // Validate unique constraints
        if (ownerRepository.existsByEmail(ownerDTO.getEmail())) {
            throw new OwnerException("Owner with this email already exists");
        }
        
        Owner owner = new Owner();
        owner.setId(ownerDTO.getId());
        owner.setOwnerName(ownerDTO.getOwnerName());
        owner.setEmail(ownerDTO.getEmail());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        owner.setGstNumber(ownerDTO.getGstNumber());
        owner.setPanNumber(ownerDTO.getPanNumber());
        owner.setBusinessName(ownerDTO.getBusinessName());
        
        return ownerRepository.save(owner);
    }
    
    public Owner getOwnerById(String id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerException("Owner not found with id: " + id));
    }

    public OwnerDTO getOwnerByIdWithDTO(String id) {
        return OwnerMapper.toDTO(ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerException("Owner not found with id: " + id)));
    }
    
    public OwnerDTO getOwnerByEmail(String email) {
        return OwnerMapper.toDTO(ownerRepository.findByEmail(email)
                .orElseThrow(() -> new OwnerException("Owner not found with email: " + email)));
    }
    
    // Function Hall operations
    @Transactional
    public FunctionHallDTO createFunctionHall(FunctionHallCreateDTO createDTO) {
        // Find or create owner
        Owner owner;
        if (createDTO.getOwner().getId() != null) {
            owner = getOwnerById(createDTO.getOwner().getId());
        } else {
            owner = createOwner(createDTO.getOwner());
        }


        
        // Create function hall
        FunctionHalls functionHall;
//        functionHallsRepository.findById(createDTO.getVenueId()).orElse(new FunctionHalls()) ;
        if (createDTO.getVenueId() != null) {
            // -------- UPDATE --------
            functionHall = functionHallsRepository.findById(createDTO.getVenueId())
                    .orElseThrow(() -> new RuntimeException("Function hall with id " + createDTO.getVenueId() + " not found"));
        } else {
            // -------- CREATE --------
            functionHall = new FunctionHalls();

            Address address = new Address();
            address.setId(UUID.randomUUID().toString());
            functionHall.setAddress(address);

            functionHall.setContactDetails(new ContactDetails());
        }
        functionHall.setFunctionHallsName(createDTO.getFunctionHallsName().toLowerCase());
        functionHall.setCostPerDay(createDTO.getCostPerDay());
        functionHall.setCapacity(createDTO.getCapacity());
        functionHall.setAbout(createDTO.getAbout());
        functionHall.setGstNumber(createDTO.getGstNumber());
        functionHall.setIsBooked(false);
        functionHall.setOwner(owner);

        
        // Set address
        if (createDTO.getAddress() != null) {
            Address address = functionHall.getAddress();
            address.setStreetName(createDTO.getAddress().getAreaName());
            address.setCity(createDTO.getAddress().getCity().toLowerCase());
            address.setPinCode(createDTO.getAddress().getPincode());
            address.setFunctionHalls(functionHall);
            functionHall.setAddress(address);
        }
        
        // Set contact details
        if (createDTO.getContactDetailsDto() != null) {
            ContactDetails contactDetails = functionHall.getContactDetails();
            contactDetails.setName(createDTO.getFunctionHallsName());
            contactDetails.setNameOfOwner(createDTO.getContactDetailsDto().getNameOfOwner());
            contactDetails.setEmail(createDTO.getContactDetailsDto().getEmail());
            contactDetails.setContactNo(createDTO.getContactDetailsDto().getContactNo());
            contactDetails.setFunctionHall(functionHall);
            functionHall.setContactDetails(contactDetails);
        }
        
        // Set amenities
        if (createDTO.getAmenities() != null) {
            for (String amenityName : createDTO.getAmenities()) {
                Eminities amenity = eminitiesRepository.findByName(amenityName)
                        .orElseGet(() -> {
                            Eminities newAmenity = new Eminities();
                            newAmenity.setName(amenityName);
                            return eminitiesRepository.save(newAmenity);
                        });

                functionHall.addAmenity(amenity);
            }
        }
        
        FunctionHalls savedFunctionHall = functionHallsRepository.save(functionHall);
        owner.addFunctionHall(savedFunctionHall);
        ownerRepository.save(owner);
        
        return toDTO(savedFunctionHall);
    }
    
    public List<FunctionHallDTO> getFunctionHallsByOwner(String ownerId) {
        Owner owner = getOwnerById(ownerId);
        return functionHallsRepository.findByOwner(owner).stream().map(FunctionHallMapper::toDTO).toList();
    }
    
    public FunctionHallDTO getFunctionHallById(Long id) {
        return toDTO(functionHallsRepository.findById(id)
                .orElseThrow(() -> new OwnerException("Function hall not found with id: " + id)));
    }

    public FunctionHalls getFunctionHallByIdWithOutDTO(Long id) {
        return functionHallsRepository.findById(id)
                .orElseThrow(() -> new OwnerException("Function hall not found with id: " + id));
    }
    
    // Booking operations
    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        FunctionHalls functionHall = getFunctionHallByIdWithOutDTO(bookingDTO.getFunctionHallId());
        
        // Check for booking conflicts
        List<Booking> conflictingBookings = bookingRepository.findConflictingBookings(
                functionHall, bookingDTO.getStartTime(), bookingDTO.getEndTime());
        
        if (!conflictingBookings.isEmpty()) {
            throw new BookingException("Function hall is already booked for the specified time period");
        }
        
        Booking booking = new Booking();
        booking.setFunctionHall(functionHall);
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndTime(bookingDTO.getEndTime());
        booking.setCustomerName(bookingDTO.getCustomerName());
        booking.setCustomerPhone(bookingDTO.getCustomerPhone());
        booking.setCustomerEmail(bookingDTO.getCustomerEmail());
        booking.setEventType(bookingDTO.getEventType());
        booking.setGuestCount(bookingDTO.getGuestCount());
        booking.setTotalAmount(bookingDTO.getTotalAmount());
        booking.setStatus(Booking.BookingStatus.CONFIRMED);
        booking.setBookingDate(LocalDateTime.now());
        
        Booking savedBooking = bookingRepository.save(booking);
        
        // Update function hall booking status
        functionHall.setIsBooked(true);
        functionHallsRepository.save(functionHall);
        
        return BookingMapper.toDTO(savedBooking);
    }
    
    public List<BookingDTO> getBookingsByFunctionHall(Long functionHallId) {
        FunctionHalls functionHall = getFunctionHallByIdWithOutDTO(functionHallId);
        return bookingRepository.findByFunctionHall(functionHall).stream().map(BookingMapper::toDTO).toList();
    }
    
    public List<BookingDTO> getUpcomingBookingsByFunctionHall(Long functionHallId) {
        FunctionHalls functionHall = getFunctionHallByIdWithOutDTO(functionHallId);
        return bookingRepository.findUpcomingBookings(functionHall, LocalDateTime.now()).stream().map(BookingMapper::toDTO).toList();
    }
    
    @Transactional
    public BookingDTO updateBookingStatus(Long bookingId, Booking.BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingException("Booking not found with id: " + bookingId));
        
        booking.setStatus(status);
        booking.setUpdatedAt(LocalDateTime.now());
        
        // Update function hall booking status if all bookings are cancelled
        if (status == Booking.BookingStatus.CANCELLED) {
            List<Booking> activeBookings = bookingRepository.findByFunctionHall(booking.getFunctionHall())
                    .stream()
                    .filter(b -> b.getStatus() == Booking.BookingStatus.CONFIRMED)
                    .collect(Collectors.toList());
            
            if (activeBookings.isEmpty()) {
                booking.getFunctionHall().setIsBooked(false);
                functionHallsRepository.save(booking.getFunctionHall());
            }
        }
        
        return BookingMapper.toDTO(bookingRepository.save(booking));
    }
}