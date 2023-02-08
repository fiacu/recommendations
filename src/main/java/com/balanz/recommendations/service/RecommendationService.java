package com.balanz.recommendations.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        log.info("Find by name {}", name);
        return repo.findByName(name);
    }

    public void saveRecommendation(Recommendation rec) {
        log.info("Save: {}", rec.getName());
        repo.save(rec);
    }

    public List<Recommendation> findRecommendations(String name) {
        List<Recommendation> recommendations = new ArrayList<>();
        if(name == null || name.isEmpty()) {
            repo.findAll().forEach(recommendations::add);
        }
        else {
            log.info("Find like name: {}", name);
            repo.findByNameContains(name).forEach(recommendations::add);
        }
        return recommendations;
    }

    @Transactional
    public void updateRecommendation(Recommendation oldRec, Recommendation newRec) {
        /*log.info("Delete: {}", oldRec.getName());
        Query q = em.createNativeQuery("DELETE FROM RecommendationInstrument ri WHERE ri.id = ?");
        q.setParameter(1, oldRec.getId());
        q.executeUpdate();
        */
        log.info("Update: {}", oldRec.getName());
        oldRec.setUpdate(new Date());
        //oldRec.setInstruments(newRec.getInstruments());
        //oldRec.setPersons(newRec.getPersons());
        repo.save(oldRec);
    }

    public void deleteRecommendation(String id) {
        log.info("Find by id: {}", id);
        Recommendation rec = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
        rec.getInstruments().clear();
        log.info("Save by id: {}", id);
        repo.save(rec);
        log.info("Delete by id: {}", id);
        repo.deleteById(id);
    }

}
