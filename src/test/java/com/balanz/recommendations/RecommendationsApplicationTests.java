package com.balanz.recommendations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecommendationsApplicationTests {
    @Autowired
    RecommendationService service;
    @Test
    void contextLoads() {
        
    }

    @Test
    void crudTest() {
        @Valid
        Recommendation rec = buildDefaultRecommendation();
        service.createRecommendation(rec);
        Optional<Recommendation> result = service.getRecommendationByName(rec.getName());
        assertEquals(true, result.isPresent());
        assertEquals(result.get().getId(), rec.getId());
        
        //update list of instruments
        rec.setInstruments(buildOptionInstrumentList(rec.getId()));
        service.deleteRecommendation(rec.getId());
        service.createRecommendation(rec);
        result = service.getRecommendationByName(rec.getName());
        assertEquals(true, result.isPresent());

    }

    private @Valid Recommendation buildDefaultRecommendation() {
        return Recommendation.builder()
        .withId("id")
        .withName("name")
        .withInstruments(buildDefaultInstrumentList("id"))
        .build();
    }

    private Set<RecommendationInstrument> buildDefaultInstrumentList(String id) {
        Set<RecommendationInstrument> result = new HashSet<>();
        result.add(RecommendationInstrument.builder().withIdRecommendation(id).withIdentifier("AL30").withOrder(1l).build());
        result.add(RecommendationInstrument.builder().withIdRecommendation(id).withIdentifier("AAPL30").withOrder(2l).build());
        return result;
    }
    
    private Set<RecommendationInstrument> buildOptionInstrumentList(String id) {
        Set<RecommendationInstrument> result = new HashSet<>();
        result.add(RecommendationInstrument.builder().withIdRecommendation(id).withIdentifier("AL30").withOrder(1l).build());
        result.add(RecommendationInstrument.builder().withIdRecommendation(id).withIdentifier("GD30").withOrder(2l).build());
        return result;
    }
}
