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
    private Long settings_id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private boolean notifications_enabled;
    @Temporal(TemporalType.TIMESTAMP)
    private Date stop_notifications;

    public Long getSettings_id() {
        return settings_id;
    }

    public void setSettings_id(Long settings_id) {
        this.settings_id = settings_id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public boolean isNotifications_enabled() {
        return notifications_enabled;
    }

    public void setNotifications_enabled(boolean notifications_enabled) {
        this.notifications_enabled = notifications_enabled;
    }

    public Date getStop_notifications() {
        return stop_notifications;
    }

    public void setStop_notifications(Date stop_notifications) {
        this.stop_notifications = stop_notifications;
    }
}
