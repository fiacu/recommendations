package com.balanz.recommendations.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ApiError {
    
    private static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
    
    private HttpStatus status;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDateTime timestamp;
    private String message;
 
    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, Exception ex) {
        this();
        this.status = status;
        this.message = ex.getLocalizedMessage();
    }
    
    public ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    @JsonIgnore
    public String getFormatedTimestamp() {
        return timestamp.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
    
 }