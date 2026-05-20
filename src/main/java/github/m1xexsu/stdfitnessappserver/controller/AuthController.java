package github.m1xexsu.stdfitnessappserver.controller;

import github.m1xexsu.stdfitnessappserver.dto.LoginRequest;
import github.m1xexsu.stdfitnessappserver.dto.LoginResponse;
import github.m1xexsu.stdfitnessappserver.dto.RegisterRequest;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import github.m1xexsu.stdfitnessappserver.service.JwtService;
import github.m1xexsu.stdfitnessappserver.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-контроллер для тестовой аутентификации.
 * <p>
 * Содержит endpoint регистрации и входа по username/password.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /**
     * Регистрирует нового пользователя.
     *
     * @param request данные регистрации
     * @return HTTP 200 с данными пользователя или HTTP 400 при ошибке
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            UserEntity user = userService.registerUser(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
            );
            String token = jwtService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(
                user.getUser_id(),
                user.getUsername(),
                "User registered successfully",
                token,
                jwtService.getTokenType(),
                jwtService.getExpirationSeconds()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * Выполняет вход пользователя.
     *
     * @param request данные для входа
     * @return HTTP 200 с результатом входа или HTTP 400 при ошибке
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            var user = userService.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

            if (!userService.validatePassword(request.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid password");
            }

            String token = jwtService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(
                user.getUser_id(),
                user.getUsername(),
                "Login successful",
                token,
                jwtService.getTokenType(),
                jwtService.getExpirationSeconds()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * DTO ошибки для ответов auth endpoint-ов.
     */
    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
