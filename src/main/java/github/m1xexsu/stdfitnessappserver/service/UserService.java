package github.m1xexsu.stdfitnessappserver.service;

import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import github.m1xexsu.stdfitnessappserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис для регистрации и базовой аутентификации пользователей.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Регистрирует нового пользователя.
     *
     * @param username имя пользователя
     * @param email email пользователя
     * @param password пароль пользователя
     * @return сохраненная сущность пользователя
     */
    public UserEntity registerUser(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAccount_status(1);
        return userRepository.save(user);
    }

    /**
     * Ищет пользователя по username.
     *
     * @param username имя пользователя
     * @return найденный пользователь или пустой результат
     */
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Ищет пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     * @return найденный пользователь или пустой результат
     */
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    //public List<UserEntity> getAllUsers() {
    //    return userRepository.findAll();
    //}

    /**
     * Сравнивает введенный пароль с сохраненным.
     *
     * @param rawPassword введенный пароль
     * @param storedPassword сохраненный пароль
     * @return {@code true}, если пароли совпадают
     */
    public boolean validatePassword(String rawPassword, String storedPassword) {
        return rawPassword.equals(storedPassword);
    }
}
