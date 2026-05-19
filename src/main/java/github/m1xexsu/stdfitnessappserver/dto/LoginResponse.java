package github.m1xexsu.stdfitnessappserver.dto;

/**
 * DTO ответа авторизации/регистрации.
 */
public class LoginResponse {
    private Long user_id;
    private String username;
    private String message;
    private String token;
    private String tokenType;
    private Long expiresIn;

    public LoginResponse() {}

    public LoginResponse(Long user_id, String username, String message) {
        this(user_id, username, message, null, null, null);
    }

    public LoginResponse(Long user_id, String username, String message, String token, String tokenType, Long expiresIn) {
        this.user_id = user_id;
        this.username = username;
        this.message = message;
        this.token = token;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
