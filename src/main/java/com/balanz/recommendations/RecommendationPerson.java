package com.balanz.recommendations;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RecomendacionesPersonas")
@IdClass(RecommendationPersonId.class)
public class RecommendationPerson {
    @Id
    @Column(name = "idRecomendacion")
    private String idRecommendation;
    @Id
    @Column(name = "idPersona")
    private Long personId;
}
