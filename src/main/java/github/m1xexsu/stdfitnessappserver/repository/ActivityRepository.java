package github.m1xexsu.stdfitnessappserver.repository;

import github.m1xexsu.stdfitnessappserver.entity.ActivityEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для операций с активностями.
 */
@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
    /**
     * Возвращает все активности указанного пользователя.
     *
     * @param user пользователь
     * @return список активностей
     */
    List<ActivityEntity> findByUser(UserEntity user);
}
