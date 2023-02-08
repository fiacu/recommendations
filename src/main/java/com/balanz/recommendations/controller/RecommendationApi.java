package com.balanz.recommendations.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.balanz.recommendations.model.ApiError;
import com.balanz.recommendations.model.Recommendation;
import com.balanz.recommendations.model.RecommendationEntry;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping
public interface RecommendationApi {

    @GetMapping(value = "/")
    @Operation(summary = "Find recommendations", tags = { "Recommendation" })
    @ApiResponses(value = { 
            @ApiResponse(
                responseCode = "200", 
                description = "Ok", 
                content = @Content(mediaType = "application/json", 
                                   array = @ArraySchema(schema = @Schema(implementation = Recommendation.class)))
            ),
            @ApiResponse(
                responseCode = "400", 
                description = "Bad request", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "Not found", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            )
        })
    ResponseEntity<List<Recommendation>> findRecommendation(@RequestParam(required = false) final String name);
    
    @PostMapping(value = "/", consumes = { "application/json" })
    @Operation(summary = "Create a recommendation", tags = { "Recommendation" })
    @ApiResponses(value = { 
            @ApiResponse(
                responseCode = "201", 
                description = "Created"
            ),
            @ApiResponse(
                responseCode = "400", 
                description = "Bad request", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            )
        })
    ResponseEntity<Void> createRecommendation(@Valid @RequestBody final RecommendationEntry recommendation);

    @GetMapping(value = "/{recommendationId}", produces = { "application/json" })
    @Operation(summary = "Get a recommendation by name", tags = { "Recommendation" })
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200", 
                description = "Ok", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Recommendation.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Bad request", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Recommendation not found", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            ),
            
        })
    ResponseEntity<Recommendation> getRecommendation(@PathVariable final String recommendationId);

    @PutMapping(value = "/{recommendationId}", consumes = { "application/json" })
    @Operation(summary = "Update a recommendation by name", tags = { "Recommendation" })
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200", 
                description = "Ok", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Recommendation.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Bad request", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Recommendation not found", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            ),
            
        })
    ResponseEntity<Void> updateRecommendation(@PathVariable final String recommendationId, @Valid @RequestBody final RecommendationEntry recommendation);

    @DeleteMapping(value = "/{recommendationId}", produces = {"application/json" })
    @Operation(summary = "Delete a recommendation", tags = { "Recommendation" })
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "204", 
                description = "No content" 
            ),
            @ApiResponse(
                responseCode = "400", 
                description = "Bad request",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            ),
        })
    ResponseEntity<Void> deleteRecommendation(@PathVariable final String recommendationId);

    @GetMapping(value = "/name/{name}", produces = { "application/json" })
    @Operation(summary = "Get a recommendation by name", tags = { "Recommendation" })
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200", 
                description = "Ok", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Recommendation.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Bad request", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Recommendation not found", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            ),
            
        })
    ResponseEntity<Recommendation> getRecommendationByName(@PathVariable final String name);

    @PutMapping(value = "/name/{name}", consumes = { "application/json" })
    @Operation(summary = "Update a recommendation by name", tags = { "Recommendation" })
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200", 
                description = "Ok", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Recommendation.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Bad request", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Recommendation not found", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            ),
            
        })
    ResponseEntity<Void> updateRecommendationByName(@PathVariable final String name, @Valid @RequestBody final RecommendationEntry recommendation);
}
