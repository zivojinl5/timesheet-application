package com.example.backend.data.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.example.backend.data.enums.Status;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@SQLDelete(sql = "UPDATE categories SET status = 'DELETED' WHERE id=?")
@SQLRestriction("status <> 'DELETED'")
@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(columnDefinition = "varchar(255) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @OneToMany(mappedBy = "category")
    private Set<ActivityEntity> entries = new HashSet<>();

    // Constructor
    public CategoryEntity(String name) {
        this.name = name;
    }
}