package com.epam.electives.model;

import lombok.Data;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rusamaha on 7/29/17.
 */

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
    private String password;
    private boolean enabled;

    public String getOnlyDate() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return birthday == null ? null : formatter.format(birthday);
    }
}
