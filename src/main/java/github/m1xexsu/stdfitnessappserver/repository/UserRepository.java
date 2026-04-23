package github.m1xexsu.stdfitnessappserver.repository;

import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для доступа к данным пользователей.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Ищет пользователя по username.
     *
     * @param username имя пользователя
     * @return найденный пользователь или пустой результат
     */
    Optional<UserEntity> findByUsername(String username);

    /**
     * Проверяет существование пользователя по username.
     *
     * @param username имя пользователя
     * @return {@code true}, если пользователь существует
     */
    boolean existsByUsername(String username);
}
