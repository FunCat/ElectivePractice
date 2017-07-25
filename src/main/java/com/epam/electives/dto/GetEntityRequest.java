package com.epam.electives.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetEntityRequest {
    Integer start;
    Integer length;

    public GetEntityRequest(Integer start, Integer length) {
        this.start = start;
        this.length = length;
    }
}