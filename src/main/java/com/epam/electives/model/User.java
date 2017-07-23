package com.epam.electives.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String middlename;
    private Date birthday;
    private Long role_id;
}
