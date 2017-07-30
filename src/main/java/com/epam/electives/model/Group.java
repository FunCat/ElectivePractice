package com.epam.electives.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by rusamaha on 7/29/17.
 */

@Data
@Entity
@Table(name = "CourseGroup")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserProfile student;
    @ManyToOne
    private Course course;
    private Long grade;
    private String review;
}
