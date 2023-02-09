package com.balanz.recommendations.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balanz.recommendations.exception.ResourceNotFoundException;
import com.balanz.recommendations.model.Recommendation;
import com.balanz.recommendations.model.RecommendationInstrument;
import com.balanz.recommendations.model.RecommendationPerson;
import com.balanz.recommendations.repository.RecommendationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecommendationService {
    @Autowired
    private RecommendationRepository repo;

    public Optional<Recommendation> getRecommendationByName(String name) {
        log.debug("Find by name {}", name);
        return repo.findByName(name);
    }

    public void saveRecommendation(Recommendation rec) {
        log.debug("Save: {}", rec.getName());
        repo.save(rec);
    }

    public List<Recommendation> findRecommendations(String name) {
        List<Recommendation> recommendations = new ArrayList<>();
        if(name == null || name.isEmpty()) {
            repo.findAll().forEach(recommendations::add);
        }
        else {
            log.debug("Find like name: {}", name);
            repo.findByNameContains(name).forEach(recommendations::add);
        }
        return recommendations;
    }

    public void deleteRecommendation(String recommendationId) {
        log.debug("Find by id: {}", recommendationId);
        repo.findById(recommendationId).orElseThrow(() -> new ResourceNotFoundException("Not found Recommendation with id = " + recommendationId));
        log.debug("Delete by id: {}", recommendationId);
        repo.deleteById(recommendationId);
    }

    public Recommendation getRecommendationById(String recommendationId) {
        log.debug("Find by id: {}", recommendationId);
        return repo.findById(recommendationId).orElseThrow(() -> new ResourceNotFoundException("Not found Recommendation with id = " + recommendationId));
    }

    public void patchRecommendation(String recommendationId, Recommendation mapRecomendation) {
        log.debug("Find by id: {}", recommendationId);
        Recommendation actual = this.getRecommendationById(recommendationId);
        log.debug("Patch id: {}", recommendationId);
        for(RecommendationInstrument i : mapRecomendation.getInstruments()) {
            if(notPresent(i, actual.getInstruments()))
                actual.getInstruments().add(i);
        }
        for(RecommendationPerson p : mapRecomendation.getPersons()) {
            if(notPresent(p, actual.getPersons()))
                actual.getPersons().add(p);
        }
        actual.setUpdate(mapRecomendation.getUpdate());
        repo.save(actual);
    }

    private boolean notPresent(RecommendationPerson p, Set<RecommendationPerson> persons) {
        return persons.stream().filter(rp -> rp.getPersonId().longValue() == p.getPersonId().longValue()).findAny().isEmpty();
    }

    private boolean notPresent(RecommendationInstrument i, List<RecommendationInstrument> instruments) {
        return instruments.stream().filter(ri -> ri.getIdentifier().equalsIgnoreCase(i.getIdentifier())).findAny().isEmpty();
    }

    public void updateRecommendation(String recommendationId, Recommendation mapRecomendation) {
        log.debug("Find by id: {}", recommendationId);
        this.getRecommendationById(recommendationId);
        log.debug("Update id: {}", recommendationId);
        this.saveRecommendation(mapRecomendation);
    }

}
