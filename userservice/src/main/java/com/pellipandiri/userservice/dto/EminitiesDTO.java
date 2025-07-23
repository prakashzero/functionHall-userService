package com.pellipandiri.userservice.dto;

public class EminitiesDTO {
    private Long id;
    private String name;

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
    
    @Override
    public String toString() {
        return "EminitiesDTO{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
