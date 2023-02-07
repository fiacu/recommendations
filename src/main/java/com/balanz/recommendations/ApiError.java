package com.balanz.recommendations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ApiError {
    
    private static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
    
    private HttpStatus status;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDateTime timestamp;
    private String message;
    private List<ApiSubError> subErrors;
 
    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status, Exception ex) {
        this();
        this.status = status;
        this.message = ex.getLocalizedMessage();
    }
    
    ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    @JsonIgnore
    public String getFormatedTimestamp() {
        return timestamp.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
    
 }