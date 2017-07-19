package com.epam.electives.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
