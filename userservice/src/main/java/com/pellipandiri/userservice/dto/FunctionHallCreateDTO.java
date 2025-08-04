package com.pellipandiri.userservice.dto;

import java.util.List;
import java.util.Map;

public class FunctionHallCreateDTO {
    private String functionHallsName;
    private Long costPerDay;
    private String capacity;
    private String about;
    private AddressDTO address;
    private Map<String, String> imagesUrl;
    private List<String> amenities;
    private ContactDetailsDto contactDetailsDto;
    private String gstNumber;
    private OwnerDTO owner;
    
    // Constructors
    public FunctionHallCreateDTO() {}
    
    // Getters and Setters
    public String getFunctionHallsName() {
        return functionHallsName;
    }
    
    public void setFunctionHallsName(String functionHallsName) {
        this.functionHallsName = functionHallsName;
    }
    
    public Long getCostPerDay() {
        return costPerDay;
    }
    
    public void setCostPerDay(Long costPerDay) {
        this.costPerDay = costPerDay;
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
    
    public AddressDTO getAddress() {
        return address;
    }
    
    public void setAddress(AddressDTO address) {
        this.address = address;
    }
    
    public Map<String, String> getImagesUrl() {
        return imagesUrl;
    }
    
    public void setImagesUrl(Map<String, String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }
    
    public List<String> getAmenities() {
        return amenities;
    }
    
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
    
    public ContactDetailsDto getContactDetailsDto() {
        return contactDetailsDto;
    }
    
    public void setContactDetailsDto(ContactDetailsDto contactDetailsDto) {
        this.contactDetailsDto = contactDetailsDto;
    }
    
    public String getGstNumber() {
        return gstNumber;
    }
    
    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }
    
    public OwnerDTO getOwner() {
        return owner;
    }
    
    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }
} 