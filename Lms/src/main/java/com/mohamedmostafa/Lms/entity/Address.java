package com.mohamedmostafa.Lms.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "address")
public class Address {


    @Id
    private Integer id;

    private String street;

    private String city;

    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
