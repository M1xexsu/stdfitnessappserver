package github.m1xexsu.stdfitnessappserver.repository;

import github.m1xexsu.stdfitnessappserver.entity.SettingsEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для пользовательских настроек.
 */
@Repository
public interface SettingsRepository extends JpaRepository<SettingsEntity, Long> {
    /**
     * Ищет настройки по пользователю.
     *
     * @param user пользователь
     * @return настройки пользователя или пустой результат
     */
    Optional<SettingsEntity> findByUser(UserEntity user);
}

