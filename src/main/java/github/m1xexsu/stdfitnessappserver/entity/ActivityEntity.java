package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * JPA-сущность дневной активности пользователя.
 */
@Entity
@Table(name = "Activity")
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long activity_id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private Date activity_date;
    private int steps;
    private int burnt;
    private boolean goal_achieved;

    public long getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(long activity_id) {
        this.activity_id = activity_id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getActivity_date() {
        return activity_date;
    }

    public void setActivity_date(Date activity_date) {
        this.activity_date = activity_date;
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

    public boolean isGoal_achieved() {
        return goal_achieved;
    }

    public void setGoal_achieved(boolean goal_achieved) {
        this.goal_achieved = goal_achieved;
    }
}
