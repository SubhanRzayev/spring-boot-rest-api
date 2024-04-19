package com.subhan.springbootrestapi.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true,name = "first_name",nullable = false)
    private String firstname;

    @Column(unique = true,name = "last_name",nullable = false)
    private String lastname;

    @Column(unique = true,name ="email",nullable = false)
    private String email;
}

