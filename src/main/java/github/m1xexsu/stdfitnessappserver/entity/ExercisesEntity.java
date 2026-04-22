package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Exercises")
public class ExercisesEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long exercise_id;

    @Column(nullable = false)
    private String name;
    private String description;
    private String file_path;
}
