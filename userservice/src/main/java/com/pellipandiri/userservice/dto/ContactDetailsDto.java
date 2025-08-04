package com.pellipandiri.userservice.dto;

public class ContactDetailsDto {

    private String name;
    private String nameOfOwner;
    private String contactNo;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfOwner() {
        return nameOfOwner;
    }

    public void setNameOfOwner(String nameOfOwner) {
        this.nameOfOwner = nameOfOwner;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContactDetailsDto(String name, String nameOfOwner, String contactNo, String email) {
        this.name = name;
        this.nameOfOwner = nameOfOwner;
        this.contactNo = contactNo;
        this.email = email;
    }
}
