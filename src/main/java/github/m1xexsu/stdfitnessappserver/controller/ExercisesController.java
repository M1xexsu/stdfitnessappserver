package github.m1xexsu.stdfitnessappserver.controller;

import github.m1xexsu.stdfitnessappserver.entity.ExercisesEntity;
import github.m1xexsu.stdfitnessappserver.service.ExercisesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контроллер для управления справочником упражнений.
 */
@RestController
@RequestMapping("/exercises")
public class ExercisesController {
    private final ExercisesService exercisesService;

    public ExercisesController(ExercisesService exercisesService) {
        this.exercisesService = exercisesService;
    }

    /**
     * Возвращает список всех упражнений.
     *
     * @return HTTP 200 со списком упражнений
     */
    @GetMapping()
    public ResponseEntity<List<ExercisesEntity>> getAllExercises() {
        return ResponseEntity.ok(exercisesService.getAllExercises());
    }

    /**
     * Возвращает упражнение по идентификатору.
     *
     * @param id идентификатор упражнения
     * @return HTTP 200 с упражнением или HTTP 404, если запись не найдена
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getExerciseById(@PathVariable Long id) {
        return exercisesService.getExerciseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Создает новое упражнение.
     *
     * @param exercise данные упражнения
     * @return HTTP 200 с созданной записью или HTTP 400 при ошибке
     */
    @PostMapping
    public ResponseEntity<?> createExercise(@RequestBody ExercisesEntity exercise) {
        try {
            ExercisesEntity created = exercisesService.createExercise(exercise);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * Обновляет существующее упражнение.
     *
     * @param id идентификатор упражнения
     * @param exercise новые данные упражнения
     * @return HTTP 200 с обновленной записью или HTTP 400 при ошибке
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExercise(@PathVariable Long id, @RequestBody ExercisesEntity exercise) {
        try {
            ExercisesEntity updated = exercisesService.updateExercise(id, exercise);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * Удаляет упражнение по идентификатору.
     *
     * @param id идентификатор упражнения
     * @return HTTP 200 с сообщением об удалении или HTTP 400 при ошибке
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id) {
        try {
            exercisesService.deleteExercise(id);
            return ResponseEntity.ok(new SuccessResponse("Exercise deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * DTO ошибки для ответов API.
     */
    public record ErrorResponse(String error) {
    }

    /**
     * DTO успешного ответа API.
     */
    public record SuccessResponse(String message) {
    }
}

