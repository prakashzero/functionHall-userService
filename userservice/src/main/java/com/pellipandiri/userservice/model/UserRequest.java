package com.pellipandiri.userservice.model;



public class UserRequest {

    private String functionName;

    private Points points;

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserRequest() {
    }

    public UserRequest(String functionName, Points points,String city) {
        this.functionName = functionName;
        this.points = points;
        this.city = city;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }
}
