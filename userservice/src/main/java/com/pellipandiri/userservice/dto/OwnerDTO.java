package com.pellipandiri.userservice.dto;

import java.util.List;

public class OwnerDTO {
    private String id;
    private String ownerName;
    private String email;
    private String phoneNumber;
    private String gstNumber;
    private String panNumber;


    private String businessName;
    private List<FunctionHallDTO> functionHallDTOList;
    
    // Constructors
    public OwnerDTO() {}
    
    public OwnerDTO(String ownerName, String email, String phoneNumber, String gstNumber, String panNumber,
                    List<FunctionHallDTO> functionHallDTOList) {
        this.ownerName = ownerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gstNumber = gstNumber;
        this.panNumber = panNumber;
        this.functionHallDTOList = functionHallDTOList;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getGstNumber() {
        return gstNumber;
    }
    
    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }
    
    public String getPanNumber() {
        return panNumber;
    }
    
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
    
    public String getBusinessName() {
        return businessName;
    }
    
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<FunctionHallDTO> getFunctionHallDTOList() {
        return functionHallDTOList;
    }

    public void setFunctionHallDTOList(List<FunctionHallDTO> functionHallDTOList) {
        this.functionHallDTOList = functionHallDTOList;
    }

} 