package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JPA-сущность упражнения из справочника.
 */
@Entity
@Table(name = "Exercises")
public class ExercisesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exercise_id;

    @Column(nullable = false)
    private String name;
    private String description;
    private String file_path;
    @JsonProperty("type")
    private String type;
    @JsonProperty("calories_burnt")
    private String calories_burnt;

    public Long getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Long exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaloriesBurnt() {
        return calories_burnt;
    }

    public void setCaloriesBurnt(String caloriesBurnt) {
        this.calories_burnt = caloriesBurnt;
    }

}
