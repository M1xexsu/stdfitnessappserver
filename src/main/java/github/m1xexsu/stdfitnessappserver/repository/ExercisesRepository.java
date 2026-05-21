package github.m1xexsu.stdfitnessappserver.repository;

import github.m1xexsu.stdfitnessappserver.entity.ExercisesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы со справочником упражнений.
 */
@Repository
public interface ExercisesRepository extends JpaRepository<ExercisesEntity, Long> {
    /**
     * Ищет упражнение по имени.
     *
     * @param name название упражнения
     * @return найденное упражнение или пустой результат
     */
    Optional<ExercisesEntity> findByName(String name);
}

