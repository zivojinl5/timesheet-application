package com.example.backend.data.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.example.backend.data.enums.Status;

import jakarta.persistence.*;
import lombok.Data;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "clients")
@SQLDelete(sql = "UPDATE clients SET status = 'DELETED' WHERE id=?")
@SQLRestriction("status <> 'DELETED'")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;

    @Column(columnDefinition = "varchar(255) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    @OneToMany(mappedBy = "customer")
    private Set<ProjectEntity> projects = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<ActivityEntity> entries = new HashSet<>();

    public ClientEntity(String name, String address, String city, String postalCode, CountryEntity country) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }
}
