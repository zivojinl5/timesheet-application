package com.example.backend.data.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.example.backend.data.enums.Role;
import com.example.backend.data.enums.Status;
import com.example.backend.data.enums.UserStatus;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET status = 'DELETED' WHERE id=?")
@SQLRestriction("status <> 'DELETED'")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private Double hours;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(columnDefinition = "varchar(255) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @ManyToMany
    @JoinTable(name = "worker_project", joinColumns = @JoinColumn(name = "worker_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<ProjectEntity> projects;

    @OneToMany(mappedBy = "lead")
    private Set<ProjectEntity> leadingProjects = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<ActivityEntity> entries = new HashSet<>();

    public UserEntity(String name, String username, String password, String email, Double hours, Role role,
    UserStatus userStatus) {
    this.name = name;
    this.username = username;
    this.password = password;
    this.email = email;
    this.hours = hours;
    this.role = role;
    this.userStatus = userStatus;
}
}
