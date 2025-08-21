package com.pellipandiri.userservice.dto;

import java.util.List;
import java.util.Map;

public class FunctionHallDTO {
    private Long venueId;
    private String functionHallsName;
    private Long costperDay;
    private Double rating;
    private String capacity;
    private AddressDTO address;
    private List<String> imagesUrl;
    private List<String> amenities;
    private String about;
    private ContactDetailsDto  contactDetailsDto;

    private List<BookingDTO> bookingDto;

    public ContactDetailsDto getContactDetailsDto() {
        return contactDetailsDto;
    }

    public void setContactDetailsDto(ContactDetailsDto contactDetailsDto) {
        this.contactDetailsDto = contactDetailsDto;
    }

    // Getters and Setters
    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public String getFunctionHallsName() {
        return functionHallsName;
    }

    public void setFunctionHallsName(String functionHallsName) {
        this.functionHallsName = functionHallsName;
    }

    public Long getCostperDay() {
        return costperDay;
    }

    public void setCostperDay(Long costperDay) {
        this.costperDay = costperDay;
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<BookingDTO> getBookingDto() {
        return bookingDto;
    }

    public void setBookingDto(List<BookingDTO> bookingDto) {
        this.bookingDto = bookingDto;
    }
}
