package com.epam.electives.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private UserProfile teacher;
    private Date startDate;
    private Date endDate;
    private Status status;

    public enum Status {
        ACTIVE,
        DELETED,
        ARCHIVE
    }
}
