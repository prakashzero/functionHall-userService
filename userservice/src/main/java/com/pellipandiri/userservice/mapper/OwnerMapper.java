package com.pellipandiri.userservice.mapper;

import com.pellipandiri.userservice.dto.OwnerDTO;
import com.pellipandiri.userservice.entities.Owner;

public class OwnerMapper {
    
    public static OwnerDTO toDTO(Owner owner) {
        if (owner == null) {
            return null;
        }
        
        OwnerDTO dto = new OwnerDTO();
        dto.setId(owner.getId());
        dto.setOwnerName(owner.getOwnerName());
        dto.setEmail(owner.getEmail());
        dto.setPhoneNumber(owner.getPhoneNumber());
        dto.setGstNumber(owner.getGstNumber());
        dto.setPanNumber(owner.getPanNumber());
        dto.setBusinessName(owner.getBusinessName());
        dto.setFunctionHallDTOList(owner.getFunctionHalls().stream().map(FunctionHallMapper::toDTO).toList());
        
        return dto;
    }
    
    public static Owner toEntity(OwnerDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Owner owner = new Owner();
        owner.setId(dto.getId());
        owner.setOwnerName(dto.getOwnerName());
        owner.setEmail(dto.getEmail());
        owner.setPhoneNumber(dto.getPhoneNumber());
        owner.setGstNumber(dto.getGstNumber());
        owner.setPanNumber(dto.getPanNumber());
        owner.setBusinessName(dto.getBusinessName());
        
        return owner;
    }
} 