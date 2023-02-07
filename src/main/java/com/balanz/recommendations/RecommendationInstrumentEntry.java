package com.balanz.recommendations;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class RecommendationInstrumentEntry {
    @NotBlank(message = "securityId is mandatory")
    private String securityId;
    private Long ordenamiento;
}
