package github.m1xexsu.stdfitnessappserver.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * JPA-сущность экспорта пользовательских данных.
 */
@Entity
@Table(name = "Export")
public class ExportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long export_id;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private Date export_date;
    private String file_path;

    public long getExport_id() {
        return export_id;
    }

    public void setExport_id(long export_id) {
        this.export_id = export_id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getExport_date() {
        return export_date;
    }

    public void setExport_date(Date export_date) {
        this.export_date = export_date;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
