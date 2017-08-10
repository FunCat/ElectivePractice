package com.epam.electives.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd MMM yy")
    private Date startDate;
    @JsonFormat(pattern = "dd MMM yy")
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
