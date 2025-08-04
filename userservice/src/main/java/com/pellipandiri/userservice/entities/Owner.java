package com.pellipandiri.userservice.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "owner_name", nullable = false)
    private String ownerName;
    
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    
    @Column(name = "gst_number", unique = true, nullable = false)
    private String gstNumber;
    
    @Column(name = "pan_number", unique = true, nullable = false)
    private String panNumber;
    
    @Column(name = "business_name")
    private String businessName;
    
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FunctionHalls> functionHalls = new ArrayList<>();
    
    // Constructors
    public Owner() {}
    
    public Owner(String ownerName, String email, String phoneNumber, String gstNumber, String panNumber) {
        this.ownerName = ownerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gstNumber = gstNumber;
        this.panNumber = panNumber;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
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
    
    public List<FunctionHalls> getFunctionHalls() {
        return functionHalls;
    }
    
    public void setFunctionHalls(List<FunctionHalls> functionHalls) {
        this.functionHalls = functionHalls;
    }
    
    // Convenience methods
    public void addFunctionHall(FunctionHalls functionHall) {
        this.functionHalls.add(functionHall);
        functionHall.setOwner(this);
    }
    
    public void removeFunctionHall(FunctionHalls functionHall) {
        this.functionHalls.remove(functionHall);
        functionHall.setOwner(null);
    }
} 