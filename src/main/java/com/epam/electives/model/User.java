package com.epam.electives.model;

import lombok.Data;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public String getOnlyDate(){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(birthday);
    }
}
