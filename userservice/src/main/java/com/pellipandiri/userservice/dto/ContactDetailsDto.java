package com.pellipandiri.userservice.dto;

public class ContactDetailsDto {

    private String name;
    private String nameOfOwner;
    private String landlineNo;
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

    public String getLandlineNo() {
        return landlineNo;
    }

    public void setLandlineNo(String landlineNo) {
        this.landlineNo = landlineNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContactDetailsDto(String name, String nameOfOwner, String landlineNo, String email) {
        this.name = name;
        this.nameOfOwner = nameOfOwner;
        this.landlineNo = landlineNo;
        this.email = email;
    }
}
