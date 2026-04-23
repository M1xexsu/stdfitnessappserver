package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * JPA-сущность пользовательских настроек.
 */
@Entity
@Table(name = "Settings")
public class SettingsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long settings_id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", table = "User")
    private UserEntity user_id;

    @Column(nullable = false)
    private boolean notifications_enabled;
    private Date stop_notifications;
}
