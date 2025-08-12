package com.pellipandiri.userservice.mapper;

import com.pellipandiri.userservice.dto.AddressDTO;
import com.pellipandiri.userservice.dto.ContactDetailsDto;
import com.pellipandiri.userservice.dto.FunctionHallDTO;
import com.pellipandiri.userservice.entities.Eminities;
import com.pellipandiri.userservice.entities.FunctionHalls;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunctionHallMapper {

    public static FunctionHallDTO toDTO(FunctionHalls functionHall) {
        if (functionHall == null) {
            return null;
        }

        FunctionHallDTO dto = new FunctionHallDTO();
        dto.setVenueId(functionHall.getId());
        dto.setFunctionHallsName(functionHall.getFunctionHallsName());
        dto.setCostperDay(functionHall.getCostPerDay());
        dto.setRating(functionHall.getRating());
        dto.setCapacity(functionHall.getCapacity());
        
        // Map address
        if (functionHall.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setAreaName(functionHall.getAddress().getStreetName()); 
            addressDTO.setCity(functionHall.getAddress().getCity());
            addressDTO.setPincode(functionHall.getAddress().getPinCode());
            dto.setAddress(addressDTO);
        }
        
        // Map images to s3Url1, s3Url2, etc.
        if (functionHall.getImages() != null) {
            List<String> imagesMap = IntStream.range(0, functionHall.getImages().size())
                .boxed()
                    .map(i->functionHall.getImages().get(i).getImagePath())
                .collect(Collectors.toList());
            dto.setImagesUrl(imagesMap);
        }
        
        // Map amenities to list of names
        if (functionHall.getAmenities() != null) {
            List<String> amenityNames = functionHall.getAmenities().stream()
                .map(Eminities::getName)
                .collect(Collectors.toList());
            dto.setAmenities(amenityNames);
        }

        //add contacts to the functionHall
        if(functionHall.getContactDetails()!=null){
            dto.setContactDetailsDto(new ContactDetailsDto(
                    functionHall.getContactDetails().getName(),
                    functionHall.getContactDetails().getNameOfOwner(),
                    functionHall.getContactDetails().getLandlineNo(),
                    functionHall.getContactDetails().getEmail()
            ));
        }
        
        return dto;
    }

}
