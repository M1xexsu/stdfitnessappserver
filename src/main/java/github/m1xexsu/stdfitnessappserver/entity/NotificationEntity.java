package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * JPA-сущность уведомления пользователя.
 */
@Entity
@Table(name = "Notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long notification_id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private String text;
    private Date date;
    private boolean is_sent;

    public Long getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(Long notification_id) {
        this.notification_id = notification_id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isIs_sent() {
        return is_sent;
    }

    public void setIs_sent(boolean is_sent) {
        this.is_sent = is_sent;
    }
}
