package github.m1xexsu.stdfitnessappserver.dto;

import java.util.Date;
import java.util.List;

/**
 * DTO с данными пользователя и опциональными связанными сущностями.
 *
 * <p>Используется для безопасной отдачи REST-ответа без поля пароля и без
 * рекурсивной сериализации JPA-сущностей.</p>
 */
@SuppressWarnings("unused")
public class UserDetailsResponse {
    private Long userId;
    private String username;
    private String email;
    private Date dateOfBirth;
    private int accountStatus;
    private ProfileResponse profile;
    private List<ActivityResponse> activities;

    public UserDetailsResponse() {
    }

    public UserDetailsResponse(Long userId, String username, String email, Date dateOfBirth, int accountStatus) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.accountStatus = accountStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public ProfileResponse getProfile() {
        return profile;
    }

    public void setProfile(ProfileResponse profile) {
        this.profile = profile;
    }

    public List<ActivityResponse> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityResponse> activities) {
        this.activities = activities;
    }

    /**
     * DTO профиля пользователя.
     */
    public static class ProfileResponse {
        private Long profileId;
        private String name;
        private boolean sex;
        private int age;
        private int length;
        private int weight;
        private int targetWeight;
        private int goalType;

        public ProfileResponse() {
        }

        public Long getProfileId() {
            return profileId;
        }

        public void setProfileId(Long profileId) {
            this.profileId = profileId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSex() {
            return sex;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getTargetWeight() {
            return targetWeight;
        }

        public void setTargetWeight(int targetWeight) {
            this.targetWeight = targetWeight;
        }

        public int getGoalType() {
            return goalType;
        }

        public void setGoalType(int goalType) {
            this.goalType = goalType;
        }
    }

    /**
     * DTO активности пользователя.
     */
    public static class ActivityResponse {
        private Long activityId;
        private Date activityDate;
        private int steps;
        private int burnt;
        private boolean goalAchieved;

        public ActivityResponse() {
        }

        public Long getActivityId() {
            return activityId;
        }

        public void setActivityId(Long activityId) {
            this.activityId = activityId;
        }

        public Date getActivityDate() {
            return activityDate;
        }

        public void setActivityDate(Date activityDate) {
            this.activityDate = activityDate;
        }

        public int getSteps() {
            return steps;
        }

        public void setSteps(int steps) {
            this.steps = steps;
        }

        public int getBurnt() {
            return burnt;
        }

        public void setBurnt(int burnt) {
            this.burnt = burnt;
        }

        public boolean isGoalAchieved() {
            return goalAchieved;
        }

        public void setGoalAchieved(boolean goalAchieved) {
            this.goalAchieved = goalAchieved;
        }
    }
}
