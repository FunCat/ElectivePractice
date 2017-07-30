package com.epam.electives.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by rusamaha on 7/29/17.
 */

public enum Role {
    ADMIN,
    TEACHER,
    STUDENT
}