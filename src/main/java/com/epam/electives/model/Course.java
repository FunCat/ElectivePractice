package com.epam.electives.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
//@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String name;
    @ManyToOne
    private UserProfile teacher;
    private Date startDate;
    private Date endDate;
    private Status status;

    public enum Status {
        ACTIVE,
        CANCELED,
        ARCHIVE
    }
}
