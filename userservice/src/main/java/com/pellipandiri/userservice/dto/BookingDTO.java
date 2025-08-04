package com.pellipandiri.userservice.dto;

import java.time.LocalDateTime;

public class BookingDTO {
    private Long id;
    private Long functionHallId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String eventType;
    private Integer guestCount;
    private Double totalAmount;
    private String status;
    
    // Constructors
    public BookingDTO() {}
    
    public BookingDTO(Long functionHallId, LocalDateTime startTime, LocalDateTime endTime, 
                     String customerName, String customerPhone) {
        this.functionHallId = functionHallId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getFunctionHallId() {
        return functionHallId;
    }
    
    public void setFunctionHallId(Long functionHallId) {
        this.functionHallId = functionHallId;
    }
    
    public LocalDateTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerPhone() {
        return customerPhone;
    }
    
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    public String getCustomerEmail() {
        return customerEmail;
    }
    
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    public String getEventType() {
        return eventType;
    }
    
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    public Integer getGuestCount() {
        return guestCount;
    }
    
    public void setGuestCount(Integer guestCount) {
        this.guestCount = guestCount;
    }
    
    public Double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
} 