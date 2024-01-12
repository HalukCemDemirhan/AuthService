package rs.authapp.AuthAppDemo.dto;


import rs.authapp.AuthAppDemo.enums.RoleType;
import rs.authapp.AuthAppDemo.model.Role;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private RoleType roleAdmin;

    public JwtAuthenticationResponse(String accessToken, RoleType r) {
        this.accessToken = accessToken;
        this.roleAdmin = r;
    }

    // Getters and setters

    public RoleType getRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(RoleType roleAdmin) {
        this.roleAdmin = roleAdmin;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}