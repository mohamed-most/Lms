package com.mohamedmostafa.Lms.entity;


import com.mohamedmostafa.Lms.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Getter
@Setter
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public class User {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue
    Integer id;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Address address;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;


    private String resetToken;
    private LocalDateTime tokenExpiry;


    public Role getRole() {
        String className = this.getClass().getSimpleName().toUpperCase(); // e.g., "ADMIN"
        return Role.valueOf(className); // converts String -> Role enum
    }


}
