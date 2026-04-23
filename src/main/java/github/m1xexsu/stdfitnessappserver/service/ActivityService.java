package github.m1xexsu.stdfitnessappserver.service;

import github.m1xexsu.stdfitnessappserver.entity.ActivityEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import github.m1xexsu.stdfitnessappserver.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис бизнес-логики для работы с активностями.
 */
@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /**
     * Сохраняет новую активность.
     *
     * @param activity сущность активности
     * @return сохраненная запись
     */
    public ActivityEntity createActivity(ActivityEntity activity) {
        return activityRepository.save(activity);
    }

    /**
     * Ищет активность по идентификатору.
     *
     * @param id идентификатор активности
     * @return найденная активность или пустой результат
     */
    public Optional<ActivityEntity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    /**
     * Возвращает все активности.
     *
     * @return список активностей
     */
    public List<ActivityEntity> getAllActivities() {
        return activityRepository.findAll();
    }

    /**
     * Возвращает активности пользователя.
     *
     * @param user пользователь
     * @return список активностей пользователя
     */
    public List<ActivityEntity> getActivitiesByUser(UserEntity user) {
        return activityRepository.findByUser(user);
    }

    /**
     * Обновляет существующую активность.
     *
     * @param id идентификатор активности
     * @param updatedActivity новые данные
     * @return обновленная активность
     */
    public ActivityEntity updateActivity(Long id, ActivityEntity updatedActivity) {
        return activityRepository.findById(id).map(activity -> {
            activity.setActivity_date(updatedActivity.getActivity_date());
            activity.setSteps(updatedActivity.getSteps());
            activity.setBurnt(updatedActivity.getBurnt());
            activity.setGoal_achieved(updatedActivity.isGoal_achieved());
            return activityRepository.save(activity);
        }).orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    /**
     * Удаляет активность по идентификатору.
     *
     * @param id идентификатор активности
     */
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
