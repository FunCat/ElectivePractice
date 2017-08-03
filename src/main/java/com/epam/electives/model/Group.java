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

    @EmbeddedId
    GroupId groupId;
    private Long grade;
    private String review;
}
