package com.balanz.recommendations.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balanz.recommendations.exception.ResourceNotFoundException;
import com.balanz.recommendations.model.Recommendation;
import com.balanz.recommendations.repository.RecommendationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecommendationService {
    @PersistenceContext
    private EntityManager em;
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

    @Transactional
    public void updateRecommendation(Recommendation oldRec, Recommendation newRec) {
        /*log.debug("Delete: {}", oldRec.getName());
        Query q = em.createNativeQuery("DELETE FROM RecommendationInstrument ri WHERE ri.id = ?");
        q.setParameter(1, oldRec.getId());
        q.executeUpdate();
        */
        log.debug("Update: {}", oldRec.getName());
        oldRec.setUpdate(new Date());
        //oldRec.setInstruments(newRec.getInstruments());
        //oldRec.setPersons(newRec.getPersons());
        repo.save(oldRec);
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

}
