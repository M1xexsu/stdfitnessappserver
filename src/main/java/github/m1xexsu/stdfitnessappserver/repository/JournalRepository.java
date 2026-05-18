package github.m1xexsu.stdfitnessappserver.repository;

import github.m1xexsu.stdfitnessappserver.entity.JournalEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для журнала тренировок.
 */
@Repository
public interface JournalRepository extends JpaRepository<JournalEntity, Long> {
    /**
     * Возвращает записи журнала конкретного пользователя.
     *
     * @param user пользователь
     * @return список записей журнала
     */
    List<JournalEntity> findByUser(UserEntity user);
}

