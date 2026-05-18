package github.m1xexsu.stdfitnessappserver.service;

import github.m1xexsu.stdfitnessappserver.entity.SettingsEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import github.m1xexsu.stdfitnessappserver.repository.SettingsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис для CRUD-операций с пользовательскими настройками.
 */
@Service
public class SettingsService {
    private final SettingsRepository settingsRepository;

    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    /**
     * Ищет настройки по пользователю.
     *
     * @param user пользователь
     * @return настройки пользователя или пустой результат
     */
    public Optional<SettingsEntity> findByUser(UserEntity user) {
        return settingsRepository.findByUser(user);
    }

    /**
     * Создает или обновляет настройки пользователя.
     *
     * @param user пользователь
     * @param settings данные настроек
     * @return сохраненные настройки
     */
    public SettingsEntity saveForUser(UserEntity user, SettingsEntity settings) {
        settings.setUser(user);
        return settingsRepository.findByUser(user)
                .map(existing -> {
                    settings.setSettings_id(existing.getSettings_id());
                    return settingsRepository.save(settings);
                })
                .orElseGet(() -> settingsRepository.save(settings));
    }

    /**
     * Удаляет настройки пользователя, если они существуют.
     *
     * @param user пользователь
     */
    public void deleteByUser(UserEntity user) {
        settingsRepository.findByUser(user).ifPresent(settingsRepository::delete);
    }
}

