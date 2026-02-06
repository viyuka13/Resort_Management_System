/*
TODO: FeedbackReviewController.java
Purpose:
 - CRUD for guest feedback and reviews.
Endpoints:
 - POST /api/v1/reservations/{id}/reviews
 - GET /api/v1/reservations/{id}/reviews
Responsibilities:
 - Use FeedbackReviewService to save feedback; link to reservation for follow-up.
File: support/controller/FeedbackReviewController.java
*/
package com.resortmanagement.system.support.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resortmanagement.system.support.entity.FeedbackReview;
import com.resortmanagement.system.support.service.FeedbackReviewService;

@RestController
@RequestMapping("/api/support/feedbackreviews")
public class FeedbackReviewController {

    private final FeedbackReviewService service;

    public FeedbackReviewController(FeedbackReviewService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FeedbackReview>> getAll() {
        // TODO: add pagination and filtering params
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackReview> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FeedbackReview> create(@RequestBody FeedbackReview entity) {
        // TODO: add validation
        return ResponseEntity.ok(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackReview> update(@PathVariable Long id, @RequestBody FeedbackReview entity) {
        // TODO: implement update logic
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
