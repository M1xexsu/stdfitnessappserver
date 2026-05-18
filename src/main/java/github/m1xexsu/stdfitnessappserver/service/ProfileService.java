package github.m1xexsu.stdfitnessappserver.service;

import github.m1xexsu.stdfitnessappserver.entity.ProfileEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import github.m1xexsu.stdfitnessappserver.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис для CRUD-операций с профилями пользователей.
 */
@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * Ищет профиль по пользователю.
     *
     * @param user пользователь
     * @return профиль пользователя или пустой результат
     */
    public Optional<ProfileEntity> findByUser(UserEntity user) {
        return profileRepository.findByUser(user);
    }

    /**
     * Создает или обновляет профиль пользователя.
     *
     * @param user пользователь
     * @param profile данные профиля
     * @return сохраненный профиль
     */
    public ProfileEntity saveForUser(UserEntity user, ProfileEntity profile) {
        profile.setUser(user);
        return profileRepository.findByUser(user)
                .map(existing -> {
                    profile.setProfile_id(existing.getProfile_id());
                    return profileRepository.save(profile);
                })
                .orElseGet(() -> profileRepository.save(profile));
    }

    /**
     * Удаляет профиль пользователя, если он существует.
     *
     * @param user пользователь
     */
    public void deleteByUser(UserEntity user) {
        profileRepository.findByUser(user).ifPresent(profileRepository::delete);
    }
}

