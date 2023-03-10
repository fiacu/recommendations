package com.balanz.recommendations.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.balanz.recommendations.model.ApiMapper;
import com.balanz.recommendations.model.Recommendation;
import com.balanz.recommendations.model.RecommendationEntry;
import com.balanz.recommendations.service.RecommendationService;

@RestController
public class RecommendationController implements RecommendationApi {
    @Autowired
    private RecommendationService service;

    @Override
    public ResponseEntity<List<Recommendation>> findRecommendation(final String name) {
        List<Recommendation> recommendations = service.findRecommendations(name);
        return new ResponseEntity<List<Recommendation>>(recommendations, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createRecommendation(@Valid final RecommendationEntry recommendation) {
        service.saveRecommendation(ApiMapper.mapRecomendation(recommendation));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Recommendation> getRecommendation(String recommendationId) {
        return new ResponseEntity<>(service.getRecommendationById(recommendationId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateRecommendation(String recommendationId,
            @Valid RecommendationEntry recommendation) {
        service.updateRecommendation(recommendationId, ApiMapper.mapRecomendation(recommendation));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Void> patchRecommendation(String recommendationId,
            @Valid RecommendationEntry recommendation) {
        service.patchRecommendation(recommendationId, ApiMapper.mapRecomendation(recommendation));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Void> deleteRecommendation(String recommendationId) {
        service.deleteRecommendation(recommendationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Recommendation> getRecommendationByName(final String name) {
        final Optional<Recommendation> recommendation = service.getRecommendationByName(name);
        if(recommendation.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(recommendation.get(), HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Void>  updateRecommendationByName(String name,
            @Valid RecommendationEntry recommendation) {
        final Optional<Recommendation> dbRecommendation = service.getRecommendationByName(name);
        if(dbRecommendation.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.saveRecommendation(ApiMapper.mapRecomendation(recommendation));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
