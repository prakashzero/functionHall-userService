package com.pellipandiri.userservice.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class FunctionHalls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String functionHallsName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "functionHall", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FunctionHallsImages> images;


    private String contactNumber;

    public FunctionHalls() {
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
}
