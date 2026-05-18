package github.m1xexsu.stdfitnessappserver.controller;

import github.m1xexsu.stdfitnessappserver.entity.SettingsEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import github.m1xexsu.stdfitnessappserver.service.SettingsService;
import github.m1xexsu.stdfitnessappserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-контроллер для пользовательских настроек.
 */
@RestController
@RequestMapping("/settings")
public class SettingsController {
    private final SettingsService settingsService;
    private final UserService userService;

    public SettingsController(SettingsService settingsService, UserService userService) {
        this.settingsService = settingsService;
        this.userService = userService;
    }

    /**
     * Возвращает настройки пользователя.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getSettings(@PathVariable Long userId) {
        return resolveUser(userId)
                .flatMap(settingsService::findByUser)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Создает или обновляет настройки пользователя.
     */
    @PostMapping("/{userId}")
    public ResponseEntity<?> createSettings(@PathVariable Long userId, @RequestBody SettingsEntity settings) {
        return resolveUser(userId)
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(settingsService.saveForUser(user, settings)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Обновляет настройки пользователя.
     */
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateSettings(@PathVariable Long userId, @RequestBody SettingsEntity settings) {
        return resolveUser(userId)
                .map(user -> ResponseEntity.ok(settingsService.saveForUser(user, settings)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Удаляет настройки пользователя.
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteSettings(@PathVariable Long userId) {
        return resolveUser(userId)
                .map(user -> {
                    settingsService.deleteByUser(user);
                    return ResponseEntity.ok(new SuccessResponse("Settings deleted successfully"));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private java.util.Optional<UserEntity> resolveUser(Long userId) {
        return userService.findById(userId);
    }

    public static class SuccessResponse {
        private final String message;

        public SuccessResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}

