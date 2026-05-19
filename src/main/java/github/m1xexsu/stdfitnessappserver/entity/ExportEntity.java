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
    private UserEntity user_id;

    @Column(nullable = false)
    private Date export_date;
    private String file_path;
}
