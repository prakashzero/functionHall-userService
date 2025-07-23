package com.pellipandiri.userservice.entities;


import jakarta.persistence.*;

@Entity
public class FunctionHallsImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "function_hall_id")
    private FunctionHalls functionId;

    private String imagePath;

    public FunctionHallsImages(FunctionHalls functionId, String imagePath) {
        this.functionId = functionId;
        this.imagePath = imagePath;
    }

    public FunctionHallsImages() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FunctionHalls getFunctionId() {
        return functionId;
    }

    public void setFunctionId(FunctionHalls functionId) {
        this.functionId = functionId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
