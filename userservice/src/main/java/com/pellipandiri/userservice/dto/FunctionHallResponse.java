package com.pellipandiri.userservice.dto;

import com.pellipandiri.userservice.entities.Address;
import com.pellipandiri.userservice.entities.Eminities;
import com.pellipandiri.userservice.entities.FunctionHallsImages;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FunctionHallResponse {
    private Long id;
    private String functionHallsName;
    private String contactNumber;
    private AddressDTO address;
    private List<FunctionHallImageDTO> images;
    private Set<EminitiesDTO> amenities;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public List<FunctionHallImageDTO> getImages() {
        return images;
    }

    public void setImages(List<FunctionHallImageDTO> images) {
        this.images = images;
    }
    
    public Set<EminitiesDTO> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<EminitiesDTO> amenities) {
        this.amenities = amenities;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    // Static factory method to create DTO from entity
    public static FunctionHallResponse fromEntity(com.pellipandiri.userservice.entities.FunctionHalls functionHall) {
        if (functionHall == null) {
            return null;
        }
        
        FunctionHallResponse dto = new FunctionHallResponse();
        dto.setId(functionHall.getId());
        dto.setFunctionHallsName(functionHall.getFunctionHallsName());
        dto.setContactNumber(functionHall.getContactNumber());
        
        // Map address
        if (functionHall.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setAreaName(functionHall.getAddress().getStreetName());
            addressDTO.setCity(functionHall.getAddress().getCity());
            addressDTO.setPincode(functionHall.getAddress().getPinCode());
            dto.setAddress(addressDTO);
        }
        
        // Map images
        if (functionHall.getImages() != null) {
            List<FunctionHallImageDTO> imageDTOs = functionHall.getImages().stream()
                .map(image -> {
                    FunctionHallImageDTO imageDTO = new FunctionHallImageDTO();
                    imageDTO.setId(image.getId());
                    imageDTO.setImageUrl(image.getImagePath());
                    imageDTO.setPrimary(false);
                    return imageDTO;
                })
                .collect(Collectors.toList());
            dto.setImages(imageDTOs);
        }
        
        // Map amenities
        if (functionHall.getAmenities() != null) {
            Set<EminitiesDTO> amenityDTOs = functionHall.getAmenities().stream()
                .map(amenity -> {
                    EminitiesDTO amenityDTO = new EminitiesDTO();
                    amenityDTO.setId(amenity.getId());
                    amenityDTO.setName(amenity.getName());
                    return amenityDTO;
                })
                .collect(Collectors.toSet());
            dto.setAmenities(amenityDTOs);
        }
        
        return dto;
    }
}

