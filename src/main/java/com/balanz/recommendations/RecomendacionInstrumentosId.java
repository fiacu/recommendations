package com.balanz.recommendations;

import java.io.Serializable;

import lombok.Data;

@Data
public class RecomendacionInstrumentosId implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String idRecommendation;
    private String identifier;
}
