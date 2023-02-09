package com.balanz.recommendations.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class RecommendationInstrumentsId implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String idRecommendation;
    private String identifier;
}
