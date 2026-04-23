package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;

/**
 * JPA-сущность профиля пользователя.
 */
@Entity
@Table(name="Profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profile_id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private String name;
    private boolean sex;
    private int age;
    private int length;
    private int weight;
    private int target_weight;
    private int goal_type;

    public Long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Long profile_id) {
        this.profile_id = profile_id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public int getTarget_weight() {
        return target_weight;
    }

    public void setTarget_weight(int target_weight) {
        this.target_weight = target_weight;
    }

    public int getGoal_type() {
        return goal_type;
    }

    public void setGoal_type(int goal_type) {
        this.goal_type = goal_type;
    }
}
