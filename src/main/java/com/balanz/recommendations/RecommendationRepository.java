package com.balanz.recommendations;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RecommendationRepository extends CrudRepository<Recommendation, String> {
    Optional<Recommendation> findByName(String name);
    List<Recommendation> findByNameContains(String name);
}
