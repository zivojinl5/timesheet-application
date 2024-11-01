package com.example.backend.data.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.example.backend.data.enums.Status;

import lombok.Data;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "activities")
@SQLDelete(sql = "UPDATE activities SET status = 'DELETED' WHERE id=?")
@SQLRestriction("status <> 'DELETED'")
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String description;
    private Double hours;
    private Double overtimeHours;

    @Column(columnDefinition = "varchar(255) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public ActivityEntity(LocalDate date, String description, Double hours, Double overtimeHours, ClientEntity client,
            ProjectEntity project, CategoryEntity category, UserEntity user) {
        this.date = date;
        this.description = description;
        this.hours = hours;
        this.overtimeHours = overtimeHours;
        this.client = client;
        this.project = project;
        this.category = category;
        this.user = user;

    }
}
