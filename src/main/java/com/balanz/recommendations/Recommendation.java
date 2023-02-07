package com.balanz.recommendations;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idRecomendacion")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<RecommendationInstrument> instruments;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idRecomendacion")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<RecommendationPerson> persons;
}
