package github.m1xexsu.stdfitnessappserver.controller;

import github.m1xexsu.stdfitnessappserver.entity.ActivityEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import github.m1xexsu.stdfitnessappserver.service.ActivityService;
import github.m1xexsu.stdfitnessappserver.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контроллер для операций с активностями пользователей.
 * <p>
 * Предоставляет тестовые CRUD-эндпоинты для сущности {@link ActivityEntity}.
 */
@RestController
@RequestMapping("/activities")
public class ActivityController {
    private final ActivityService activityService;
    private final UserService userService;

    public ActivityController(ActivityService activityService, UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }

    /**
     * Возвращает список всех активностей.
     *
     * @return HTTP 200 со списком активностей
     */
    @GetMapping
    public ResponseEntity<List<ActivityEntity>> getAllActivities() {
        return ResponseEntity.ok(activityService.getAllActivities());
    }

    /**
     * Возвращает активность по идентификатору.
     *
     * @param id идентификатор активности
     * @return HTTP 200 с активностью или HTTP 404, если запись не найдена
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getActivityById(@PathVariable Long id) {
        return activityService.getActivityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Возвращает активности конкретного пользователя.
     *
     * @param userId идентификатор пользователя
     * @return HTTP 200 со списком активностей пользователя или HTTP 400 при ошибке
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getActivitiesByUser(@PathVariable Long userId) {
        try {
            UserEntity user = userService.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return ResponseEntity.ok(activityService.getActivitiesByUser(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * Создает новую активность.
     *
     * @param activity данные активности
     * @return HTTP 200 с созданной активностью или HTTP 400 при ошибке
     */
    @PostMapping
    public ResponseEntity<?> createActivity(@RequestBody ActivityEntity activity) {
        try {
            ActivityEntity created = activityService.createActivity(activity);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * Обновляет существующую активность.
     *
     * @param id идентификатор активности
     * @param activity новые данные активности
     * @return HTTP 200 с обновленной записью или HTTP 400 при ошибке
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateActivity(@PathVariable Long id, @RequestBody ActivityEntity activity) {
        try {
            ActivityEntity updated = activityService.updateActivity(id, activity);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * Удаляет активность по идентификатору.
     *
     * @param id идентификатор активности
     * @return HTTP 200 с сообщением об удалении или HTTP 400 при ошибке
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable Long id) {
        try {
            activityService.deleteActivity(id);
            return ResponseEntity.ok(new SuccessResponse("Activity deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * Стандартный DTO для ошибок API.
     */
    public static class ErrorResponse {
        private final String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }

    /**
     * Стандартный DTO для успешного ответа API.
     */
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
