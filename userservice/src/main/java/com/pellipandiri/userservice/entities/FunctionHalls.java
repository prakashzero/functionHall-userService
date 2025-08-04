package com.pellipandiri.userservice.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class FunctionHalls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String functionHallsName;
    
    @Column(name = "cost_per_day")
    private Long costPerDay;
    
    @Column(name = "rating")
    private Double rating;
    

    @Column(name = "about", columnDefinition = "TEXT")
    private String about;
    
    @Column(name = "capacity")
    private String capacity;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;



    @OneToMany(mappedBy = "functionId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FunctionHallsImages> images;
    
    @OneToMany(mappedBy = "functionHall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();
    
    @OneToOne(mappedBy = "functionHall", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ContactDetails contactDetails;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "function_hall_amenities",
        joinColumns = @JoinColumn(name = "function_hall_id"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Eminities> amenities = new HashSet<>();

    private String contactNumber;
    
    @Column(name = "gst_number")
    private String gstNumber;
    
    @Column(name = "is_booked")
    private Boolean isBooked = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    
    @OneToMany(mappedBy = "functionHall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings = new ArrayList<>();

    public FunctionHalls() {
        this.costPerDay = (Long) 0L;
        this.rating = (Double) 0.0;
    }

    public FunctionHalls(String functionHallsName, Address address, String contactNumber) {
        this.functionHallsName = functionHallsName;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctionHallsName() {
        return functionHallsName;
    }

    public void setFunctionHallsName(String functionHallsName) {
        this.functionHallsName = functionHallsName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Set<Eminities> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<Eminities> amenities) {
        this.amenities = amenities;
    }

    // Convenience methods for managing amenities
    public void addAmenity(Eminities amenity) {
        this.amenities.add(amenity);
        amenity.getFunctionHalls().add(this);
    }

    public void removeAmenity(Eminities amenity) {
        this.amenities.remove(amenity);
        amenity.getFunctionHalls().remove(this);
    }
    
    public Long getCostPerDay() {
        return costPerDay;
    }
    
    public void setCostPerDay(Long costPerDay) {
        this.costPerDay = costPerDay;
    }
    
    public Double getRating() {
        return rating;
    }
    
    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    public String getCapacity() {
        return capacity;
    }
    
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    
    public String getAbout() {
        return about;
    }
    
    public void setAbout(String about) {
        this.about = about;
    }


    public List<FunctionHallsImages> getImages() {
        return images;
    }

    public void setImages(List<FunctionHallsImages> images) {
        this.images = images;
    }
    
    public List<Rating> getRatings() {
        return ratings;
    }
    
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    
    public ContactDetails getContactDetails() {
        return contactDetails;
    }
    
    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
        if (contactDetails != null) {
            contactDetails.setFunctionHall(this);
        }
    }
    
    public String getGstNumber() {
        return gstNumber;
    }
    
    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }
    
    public Boolean getIsBooked() {
        return isBooked;
    }
    
    public void setIsBooked(Boolean isBooked) {
        this.isBooked = isBooked;
    }
    
    public Owner getOwner() {
        return owner;
    }
    
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }
    
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    
    // Convenience methods for bookings
    public void addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setFunctionHall(this);
    }
    
    public void removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setFunctionHall(null);
    }
}
