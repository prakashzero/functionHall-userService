package com.pellipandiri.userservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "contact_details")
public class ContactDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "name_of_owner", nullable = false)
    private String nameOfOwner;
    
    @Column(name = "contact_no", nullable = false)
    private String contactNo;
    
    @Column(name = "landline_no")
    private String landlineNo;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "function_hall_id", unique = true, nullable = false)
    private FunctionHalls functionHall;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public FunctionHalls getFunctionHall() {
        return functionHall;
    }
    
    public void setFunctionHall(FunctionHalls functionHall) {
        this.functionHall = functionHall;
    }
}
