package com.mohamedmostafa.Lms.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "admins")
@Data
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class Admin {
}
