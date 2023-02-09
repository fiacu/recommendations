package com.balanz.recommendations.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Recomendaciones")
public class Recommendation {
    @Id
    @Column(name = "idRecomendacion")
    private String id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "fecha")
    private Date update;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRecommendation", fetch = FetchType.EAGER, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RecommendationInstrument> instruments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRecommendation", fetch = FetchType.EAGER, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<RecommendationPerson> persons;
}
