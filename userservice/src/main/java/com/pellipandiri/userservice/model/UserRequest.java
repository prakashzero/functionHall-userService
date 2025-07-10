package com.pellipandiri.userservice.model;



public class UserRequest {

    private String functionName;

    private Points points;

    public UserRequest() {
    }

    public UserRequest(String functionName, Points points) {
        this.functionName = functionName;
        this.points = points;
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
