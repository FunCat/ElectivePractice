package com.epam.electives.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CourseGroup")
public class Group {

    @EmbeddedId
    GroupId groupId;
    private Long grade;
    private String review;
}
