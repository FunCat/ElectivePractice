package com.epam.electives.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class GroupId implements Serializable {
    @ManyToOne
    private UserProfile student;
    @ManyToOne
    private Course course;
}
