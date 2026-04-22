package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long notification_id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id", table = "User")
    private UserEntity user_id;

    @Column(nullable = false)
    private String text;
    private Date date;
    private boolean is_sent;
}
