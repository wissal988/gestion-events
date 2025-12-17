package com.projetiw.authservice.dto;

public class AuthResponse {
    private String message;
    private String role;
    private Long userId;

    public AuthResponse(String message, String role, Long userId) {
        this.message = message;
        this.role = role;
        this.userId = userId;
    }

    public String getMessage() { return message; }
    public String getRole() { return role; }
    public Long getUserId() { return userId; }

    public void setMessage(String message) { this.message = message; }
    public void setRole(String role) { this.role = role; }
    public void setUserId(Long userId) { this.userId = userId; }
}
