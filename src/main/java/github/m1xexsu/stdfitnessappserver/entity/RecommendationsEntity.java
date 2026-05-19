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
    private UserEntity user_id;

    @ManyToOne
    @JoinColumn(name="exercise_id", referencedColumnName = "exercise_id")
    private ExercisesEntity exercise_id;


    @Column(nullable = false)
    private Date date;
}
