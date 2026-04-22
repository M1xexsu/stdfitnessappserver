package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Activity")
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long activity_id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id", table = "User")
    private UserEntity user_id;

    @Column(nullable = false)
    private Date activity_date;
    private int steps;
    private int burnt;
    private boolean goal_achieved;
}
