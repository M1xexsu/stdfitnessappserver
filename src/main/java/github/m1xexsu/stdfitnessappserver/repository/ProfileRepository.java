package github.m1xexsu.stdfitnessappserver.repository;

import github.m1xexsu.stdfitnessappserver.entity.ProfileEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для профилей пользователей.
 */
@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    /**
     * Ищет профиль по пользователю.
     *
     * @param user пользователь
     * @return профиль пользователя или пустой результат
     */
    Optional<ProfileEntity> findByUser(UserEntity user);
}
