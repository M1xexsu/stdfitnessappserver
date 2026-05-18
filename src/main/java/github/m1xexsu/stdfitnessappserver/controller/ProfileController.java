package github.m1xexsu.stdfitnessappserver.controller;

import github.m1xexsu.stdfitnessappserver.entity.ProfileEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import github.m1xexsu.stdfitnessappserver.service.ProfileService;
import github.m1xexsu.stdfitnessappserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-контроллер для профиля пользователя.
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    /**
     * Возвращает профиль пользователя по его ID.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfile(@PathVariable Long userId) {
        return resolveUser(userId)
                .flatMap(profileService::findByUser)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Создает или обновляет профиль пользователя.
     */
    @PostMapping("/{userId}")
    public ResponseEntity<?> createProfile(@PathVariable Long userId, @RequestBody ProfileEntity profile) {
        return resolveUser(userId)
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(profileService.saveForUser(user, profile)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Обновляет профиль пользователя.
     */
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateProfile(@PathVariable Long userId, @RequestBody ProfileEntity profile) {
        return resolveUser(userId)
                .map(user -> ResponseEntity.ok(profileService.saveForUser(user, profile)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Удаляет профиль пользователя.
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long userId) {
        return resolveUser(userId)
                .map(user -> {
                    profileService.deleteByUser(user);
                    return ResponseEntity.ok(new SuccessResponse("Profile deleted successfully"));
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

