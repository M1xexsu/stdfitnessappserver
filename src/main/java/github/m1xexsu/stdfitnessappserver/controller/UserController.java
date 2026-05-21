package github.m1xexsu.stdfitnessappserver.controller;

import github.m1xexsu.stdfitnessappserver.dto.RegisterRequest;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import github.m1xexsu.stdfitnessappserver.service.UserService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**
 * REST-контроллер для получения данных пользователей по идентификатору.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Set<String> SUPPORTED_INCLUDES = Set.of("profile", "activities");

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Возвращает пользователя по ID.
     *
     * <p>Дополнительно можно запросить связанные данные через параметр {@code include},
     * например: {@code /user/1?include=profile,activities}.</p>
     *
     * @param id идентификатор пользователя
     * @param include список связанных сущностей через запятую
     * @return данные пользователя, 404 если пользователь не найден, 400 если передан неподдерживаемый include
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id, @RequestParam(name = "include", required = false) String include) {
        try {
            Set<String> includes = parseIncludes(include);
            return userService.getUserDetailsById(id, includes)
                    .<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> changeUserCreds(@PathVariable long id, @RequestBody RegisterRequest request)
    {
        try
        {
            return ResponseEntity.ok(userService.changeCreds(id, request.getUsername(), request.getEmail()));
        }
        catch (IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/{id}/recommendation")
    public ResponseEntity<?> getRecomendation(@PathVariable long id)
    {
        try
        {
            return ResponseEntity.ok(userService.getRecomendation());
        }
        catch (IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> nuke(@PathVariable long id)
    {
        try
        {
            return ResponseEntity.ok(userService.nuke(id));
        }
        catch (IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    private Set<String> parseIncludes(String include) {
        if (include == null || include.isBlank()) {
            return Set.of();
        }

        Set<String> parsedIncludes = new LinkedHashSet<>();
        for (String rawValue : include.split(",")) {
            String normalized = rawValue.trim().toLowerCase(Locale.ROOT);
            if (normalized.isEmpty()) {
                continue;
            }
            if (!SUPPORTED_INCLUDES.contains(normalized)) {
                throw new IllegalArgumentException("Unsupported include value: " + normalized);
            }
            parsedIncludes.add(normalized);
        }
        return parsedIncludes;
    }

    /**
     * DTO для ошибки.
     */
    public static class ErrorResponse {
        public final String error;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }
}
