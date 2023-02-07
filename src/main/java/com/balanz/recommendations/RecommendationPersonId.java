package com.balanz.recommendations;

import java.io.Serializable;

import lombok.Data;

@Data
public class RecommendationPersonId implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String idRecommendation;
    private Long personId;
}
