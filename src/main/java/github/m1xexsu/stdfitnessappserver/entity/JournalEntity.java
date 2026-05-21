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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journal_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @ManyToMany
    @JoinTable(
        name = "Journal_Exercises",
        joinColumns = @JoinColumn(name = "journal_id"),
        inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<ExercisesEntity> exercises;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private int score;
    private int burnt;

    public Long getJournal_id() {
        return journal_id;
    }

    public void setJournal_id(Long journal_id) {
        this.journal_id = journal_id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<ExercisesEntity> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExercisesEntity> exercises) {
        this.exercises = exercises;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBurnt() {
        return burnt;
    }

    public void setBurnt(int burnt) {
        this.burnt = burnt;
    }

}
