package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "recommendations")
public class RecommendationsEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long recommendation_id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id", table = "User")
    private UserEntity user_id;

    @ManyToOne
    @JoinColumn(name="exercise_id", referencedColumnName = "exercise_id", table = "Exercises")
    private ExercisesEntity exercise_id;


    @Column(nullable = false)
    private Date date;
}
