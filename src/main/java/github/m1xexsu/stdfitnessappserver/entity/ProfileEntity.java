package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profile_id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", table = "User")
    private UserEntity user_id;

    @Column(nullable = false)
    private String name;
    private boolean sex;
    private int age;
    private int length;
    private int weight;
    private int target_weight;
    private int goal_type;
}
