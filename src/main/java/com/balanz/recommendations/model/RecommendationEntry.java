package com.balanz.recommendations.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class RecommendationEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    //UUID
    @NotBlank(message = "Id is mandatory")
    private String id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotEmpty(message = "At least one instrument is required.")
    private List<RecommendationInstrumentEntry> instrumentIds;
    private List<Long> personsIds;
}