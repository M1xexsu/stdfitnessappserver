package github.m1xexsu.stdfitnessappserver.service;

import github.m1xexsu.stdfitnessappserver.entity.JournalEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import github.m1xexsu.stdfitnessappserver.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для CRUD-операций с журналом тренировок.
 */
@Service
public class JournalService {
    private final JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    /**
     * Возвращает записи журнала пользователя.
     *
     * @param user пользователь
     * @return список записей журнала
     */
    public List<JournalEntity> findByUser(UserEntity user) {
        return journalRepository.findByUser(user).stream()
                .sorted(Comparator.comparing(JournalEntity::getDate, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .toList();
    }

    /**
     * Ищет запись по идентификатору.
     *
     * @param id идентификатор записи
     * @return найденная запись или пустой результат
     */
    public Optional<JournalEntity> findById(Long id) {
        return journalRepository.findById(id);
    }

    /**
     * Создает запись журнала для пользователя.
     *
     * @param user пользователь
     * @param journal данные записи
     * @return сохраненная запись
     */
    public JournalEntity saveForUser(UserEntity user, JournalEntity journal) {
        journal.setUser(user);
        return journalRepository.save(journal);
    }

    /**
     * Обновляет существующую запись журнала.
     *
     * @param id идентификатор записи
     * @param updated новые данные
     * @return обновленная запись
     */
    public JournalEntity updateJournal(Long id, JournalEntity updated) {
        return journalRepository.findById(id)
                .map(existing -> {
                    existing.setDate(updated.getDate());
                    existing.setTime_minutes(updated.getTime_minutes());
                    existing.setScore(updated.getScore());
                    existing.setBurnt(updated.getBurnt());
                    existing.setExercises(updated.getExercises());
                    return journalRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Journal entry not found"));
    }

    /**
     * Удаляет запись журнала.
     *
     * @param id идентификатор записи
     */
    public void deleteById(Long id) {
        journalRepository.deleteById(id);
    }
}

