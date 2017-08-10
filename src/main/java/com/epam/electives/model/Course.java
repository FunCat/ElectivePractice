package com.epam.electives.model;

import com.epam.electives.support.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.text.SimpleDateFormat;
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
    @JsonSerialize(using=JsonDateSerializer.class)
    private Date startDate;
    @JsonSerialize(using=JsonDateSerializer.class)
    private Date endDate;
    @Type(type="text")
    private String description;
    private Status status;

    public String dateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }

    public enum Status {
        ACTIVE,
        CANCELED,
        ARCHIVE
    }
}
