package github.m1xexsu.stdfitnessappserver.service;

import github.m1xexsu.stdfitnessappserver.entity.ExercisesEntity;
import github.m1xexsu.stdfitnessappserver.repository.ExercisesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы со справочником упражнений.
 */
@Service
public class ExercisesService {
    private final ExercisesRepository exercisesRepository;

    public ExercisesService(ExercisesRepository exercisesRepository) {
        this.exercisesRepository = exercisesRepository;
    }

    /**
     * Возвращает все упражнения.
     *
     * @return список упражнений
     */
    public List<ExercisesEntity> getAllExercises() {
        return exercisesRepository.findAll();
    }

    /**
     * Ищет упражнение по идентификатору.
     *
     * @param id идентификатор упражнения
     * @return найденное упражнение или пустой результат
     */
    public Optional<ExercisesEntity> getExerciseById(Long id) {
        return exercisesRepository.findById(id);
    }

    /**
     * Ищет упражнение по имени.
     *
     * @param name название упражнения
     * @return найденное упражнение или пустой результат
     */
    public Optional<ExercisesEntity> getExerciseByName(String name) {
        return exercisesRepository.findByName(name);
    }

    /**
     * Создает новое упражнение.
     *
     * @param exercise данные упражнения
     * @return сохраненная сущность упражнения
     */
    public ExercisesEntity createExercise(ExercisesEntity exercise) {
        return exercisesRepository.save(exercise);
    }

    /**
     * Обновляет существующее упражнение.
     *
     * @param id идентификатор упражнения
     * @param updatedExercise новые данные упражнения
     * @return обновленное упражнение
     */
    public ExercisesEntity updateExercise(Long id, ExercisesEntity updatedExercise) {
        return exercisesRepository.findById(id).map(exercise -> {
            exercise.setName(updatedExercise.getName());
            exercise.setDescription(updatedExercise.getDescription());
            exercise.setFile_path(updatedExercise.getFile_path());
            return exercisesRepository.save(exercise);
        }).orElseThrow(() -> new RuntimeException("Exercise not found"));
    }

    /**
     * Удаляет упражнение по идентификатору.
     *
     * @param id идентификатор упражнения
     */
    public void deleteExercise(Long id) {
        exercisesRepository.deleteById(id);
    }
}

