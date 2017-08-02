package com.epam.electives.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rusamaha on 7/29/17.
 */

@Data
@Entity
@Table(name = "CourseGroup")
public class Group {

    @EmbeddedId
    GroupId groupId;
    private Long grade;
    private String review;
}

@Embeddable
class GroupId implements Serializable{
    @ManyToOne
    private UserProfile student;
    @ManyToOne
    private Course course;
}
