package github.m1xexsu.stdfitnessappserver.controller;

import github.m1xexsu.stdfitnessappserver.entity.JournalEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import github.m1xexsu.stdfitnessappserver.service.JournalService;
import github.m1xexsu.stdfitnessappserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-контроллер для журнала тренировок.
 */
@RestController
@RequestMapping("/journal")
public class JournalController {
    private final JournalService journalService;
    private final UserService userService;

    public JournalController(JournalService journalService, UserService userService) {
        this.journalService = journalService;
        this.userService = userService;
    }

    /**
     * Возвращает все записи журнала конкретного пользователя.
     */
    @GetMapping("/user/{userId}/history")
    public ResponseEntity<?> getJournalByUser(@PathVariable Long userId) {
        return resolveUser(userId)
                .map(user -> ResponseEntity.ok(journalService.findByUser(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Возвращает одну запись журнала по ID.
     */
    @GetMapping("/entry/{journalId}")
    public ResponseEntity<?> getJournalEntry(@PathVariable Long journalId) {
        return journalService.findById(journalId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Создает запись журнала для пользователя.
     */
    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createJournalEntry(@PathVariable Long userId, @RequestBody JournalEntity journal) {
        return resolveUser(userId)
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(journalService.saveForUser(user, journal)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Обновляет запись журнала.
     */
    @PutMapping("/entry/{journalId}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable Long journalId, @RequestBody JournalEntity journal) {
        try {
            return ResponseEntity.ok(journalService.updateJournal(journalId, journal));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаляет запись журнала.
     */
    @DeleteMapping("/entry/{journalId}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable Long journalId) {
        return journalService.findById(journalId)
                .map(entry -> {
                    journalService.deleteById(journalId);
                    return ResponseEntity.ok(new SuccessResponse("Journal entry deleted successfully"));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private java.util.Optional<UserEntity> resolveUser(Long userId) {
        return userService.findById(userId);
    }

    /**
     * DTO для успешного ответа.
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


