package com.volounteerfinder.mvcserver.repository.dto;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String username;

    // You can add more fields like roles, if needed

    public JwtResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    // Getters and Setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // You can add more getters and setters for additional fields
}
