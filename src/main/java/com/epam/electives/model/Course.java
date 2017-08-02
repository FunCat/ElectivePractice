package com.epam.electives.model;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
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
    @Type(type="text")
    private String description;
    private Status status;

    public enum Status {
        ACTIVE,
        CANCELED,
        ARCHIVE
    }
}
