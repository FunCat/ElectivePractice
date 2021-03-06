package com.epam.electives.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String surname;
    private Date birthday;
    @Column(unique = true)
    private String login;
    @JsonIgnore
    private String password;
    private boolean enabled;

    public String getOnlyDate() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return birthday == null ? null : formatter.format(birthday);
    }
}
