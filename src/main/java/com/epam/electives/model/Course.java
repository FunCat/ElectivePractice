package com.epam.electives.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String name;
    private Date createDate;
    private Date updateDate;
    private Status status;

    public enum Status {
        ACTIVE,
        DELETED,
        ARCHIVE
    }
}
