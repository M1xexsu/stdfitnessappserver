package github.m1xexsu.stdfitnessappserver.service;

import github.m1xexsu.stdfitnessappserver.dto.UserDetailsResponse;
import github.m1xexsu.stdfitnessappserver.entity.ActivityEntity;
import github.m1xexsu.stdfitnessappserver.entity.ProfileEntity;
import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import github.m1xexsu.stdfitnessappserver.repository.ActivityRepository;
import github.m1xexsu.stdfitnessappserver.repository.ProfileRepository;
import github.m1xexsu.stdfitnessappserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис для регистрации и базовой аутентификации пользователей.
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ActivityRepository activityRepository;

    public UserService(UserRepository userRepository, ProfileRepository profileRepository, ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.activityRepository = activityRepository;
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

    /**
     * Возвращает безопасный DTO пользователя и, при необходимости, связанные сущности.
     *
     * @param id идентификатор пользователя
     * @param includes набор запрошенных связанных сущностей (например, {@code profile}, {@code activities})
     * @return DTO пользователя или пустой результат, если пользователь не найден
     */
    public Optional<UserDetailsResponse> getUserDetailsById(Long id, Set<String> includes) {
        Set<String> normalizedIncludes = normalizeIncludes(includes);

        return userRepository.findById(id).map(user -> {
            UserDetailsResponse response = new UserDetailsResponse(
                    user.getUser_id(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getDate_of_birth(),
                    user.getAccount_status()
            );

            if (normalizedIncludes.contains("profile")) {
                profileRepository.findByUser(user).ifPresent(profile -> response.setProfile(toProfileResponse(profile)));
            }

            if (normalizedIncludes.contains("activities")) {
                List<UserDetailsResponse.ActivityResponse> activities = activityRepository.findByUser(user)
                        .stream()
                        .map(this::toActivityResponse)
                        .collect(Collectors.toList());
                response.setActivities(activities);
            }

            return response;
        });
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

    private Set<String> normalizeIncludes(Set<String> includes) {
        if (includes == null || includes.isEmpty()) {
            return Set.of();
        }

        return includes.stream()
                .filter(value -> value != null && !value.isBlank())
                .map(value -> value.trim().toLowerCase(Locale.ROOT))
                .collect(Collectors.toSet());
    }

    private UserDetailsResponse.ProfileResponse toProfileResponse(ProfileEntity profile) {
        UserDetailsResponse.ProfileResponse response = new UserDetailsResponse.ProfileResponse();
        response.setProfileId(profile.getProfile_id());
        response.setName(profile.getName());
        response.setSex(profile.isSex());
        response.setAge(profile.getAge());
        response.setLength(profile.getLength());
        response.setWeight(profile.getWeight());
        response.setTargetWeight(profile.getTarget_weight());
        response.setGoalType(profile.getGoal_type());
        return response;
    }

    private UserDetailsResponse.ActivityResponse toActivityResponse(ActivityEntity activity) {
        UserDetailsResponse.ActivityResponse response = new UserDetailsResponse.ActivityResponse();
        response.setActivityId(activity.getActivity_id());
        response.setActivityDate(activity.getActivity_date());
        response.setSteps(activity.getSteps());
        response.setBurnt(activity.getBurnt());
        response.setGoalAchieved(activity.isGoal_achieved());
        return response;
    }
}
