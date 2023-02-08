package com.balanz.recommendations.model;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ApiMapper {

    public static Recommendation mapRecomendation(RecommendationEntry recommendation) {
        return Recommendation.builder()
        .withId(recommendation.getId())
        .withName(recommendation.getName())
        .withUpdate(new Date())
        .withInstruments(mapInstruments(recommendation.getId(), recommendation.getInstrumentIds()))
        //.withPersons(mapPersons(recommendation.getId(), recommendation.getPersonsIds()))
        .build();
    }

    public static Set<RecommendationPerson> mapPersons(String id, List<Long> personsIds) {
        if(personsIds == null) return null;
        return personsIds.stream().map(p -> RecommendationPerson.builder()
                .withIdRecommendation(id)
                .withPersonId(p)
                .build()).collect(Collectors.toSet());
    }

    public static List<RecommendationInstrument> mapInstruments(String id, List<RecommendationInstrumentEntry> instrumentIds) {
        return instrumentIds.stream().map(i -> RecommendationInstrument.builder()
                .withIdRecommendation(id)
                .withIdentifier(i.getSecurityId())
                .withOrder(i.getOrdenamiento())
                .build()).collect(Collectors.toList());
    }
}
