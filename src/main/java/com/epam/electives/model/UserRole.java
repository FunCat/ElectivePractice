package com.epam.electives.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_role;
    @ManyToOne
    private UserProfile user;
    private String authority;
    private String testfield;
}
