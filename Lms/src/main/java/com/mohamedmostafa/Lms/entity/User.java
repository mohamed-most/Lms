package com.mohamedmostafa.Lms.entity;


import com.mohamedmostafa.Lms.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@SuperBuilder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Address address;


    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;


    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}
