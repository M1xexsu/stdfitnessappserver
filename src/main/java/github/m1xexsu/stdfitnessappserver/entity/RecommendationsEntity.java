package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * JPA-сущность рекомендаций упражнений пользователю.
 */
@Entity
@Table(name = "recommendations")
public class RecommendationsEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long recommendation_id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="exercise_id", referencedColumnName = "exercise_id")
    private ExercisesEntity exercise;


    @Column(nullable = false)
    private Date date;

    public Long getRecommendation_id() {
        return recommendation_id;
    }

    public void setRecommendation_id(Long recommendation_id) {
        this.recommendation_id = recommendation_id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ExercisesEntity getExercise() {
        return exercise;
    }

    public void setExercise(ExercisesEntity exercise) {
        this.exercise = exercise;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
