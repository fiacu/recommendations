package com.balanz.recommendations.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RecomendacionesInstrumentos")
@IdClass(RecommendationInstrumentsId.class)
public class RecommendationInstrument {
    @Id
    @Column(name = "idRecomendacion")
    private String idRecommendation;
    @Id
    @Column(name = "instrumento")
    private String identifier;
    @Column(name = "ordenamiento")
    private Long order;
}
