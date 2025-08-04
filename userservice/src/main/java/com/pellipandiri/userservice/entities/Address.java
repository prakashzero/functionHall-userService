package com.pellipandiri.userservice.entities;

import jakarta.persistence.*;


@Entity
public class Address {



    @Id
    private String id;

    private String streetName;

    private String city;

    private String pinCode;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private FunctionHalls functionHalls;

    public Address() {
    }

    public Address(String streetName, String city, String pinCode, FunctionHalls functionHalls) {
        this.streetName = streetName;
        this.city = city;
        this.pinCode = pinCode;
        this.functionHalls = functionHalls;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public FunctionHalls getFunctionHalls() {
        return functionHalls;
    }

    public void setFunctionHalls(FunctionHalls functionHalls) {
        this.functionHalls = functionHalls;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
