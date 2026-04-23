package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * JPA-сущность записи журнала тренировок.
 */
@Entity
@Table(name="Journal")
public class JournalEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long journal_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", table = "User")
    private UserEntity user_id;

    @OneToMany
    @JoinColumn(name = "exercise_id", referencedColumnName = "exercise_id", table = "Exercises")
    private List<ExercisesEntity> exercise_id;

    @Column(nullable = false)
    private Date date;
    private int time_minutes;
    private int score;
    private int burnt;

}
