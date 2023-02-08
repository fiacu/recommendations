package com.balanz.recommendations.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.balanz.recommendations.model.Recommendation;

public interface RecommendationRepository extends CrudRepository<Recommendation, String> {
    Optional<Recommendation> findByName(String name);
    List<Recommendation> findByNameContains(String name);
}
