package github.m1xexsu.stdfitnessappserver.dto;

/**
 * DTO ответа авторизации/регистрации.
 */
public class LoginResponse {
    private Long user_id;
    private String username;
    private String message;

    public LoginResponse() {}

    public LoginResponse(Long user_id, String username, String message) {
        this.user_id = user_id;
        this.username = username;
        this.message = message;
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
}
