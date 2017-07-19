package com.epam.electives.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCourseRequest {
    Integer start;
    Integer length;

}