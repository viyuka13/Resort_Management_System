package com.resortmanagement.system.support.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.support.entity.FeedbackReview;
import com.resortmanagement.system.support.repository.FeedbackReviewRepository;

@Service
public class FeedbackReviewService {

    private final FeedbackReviewRepository repository;

    public FeedbackReviewService(FeedbackReviewRepository repository) {
        this.repository = repository;
    }

    public List<FeedbackReview> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<FeedbackReview> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public FeedbackReview save(FeedbackReview entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
