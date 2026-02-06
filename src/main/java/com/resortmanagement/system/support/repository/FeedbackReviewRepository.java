package com.resortmanagement.system.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.support.entity.FeedbackReview;

@Repository
public interface FeedbackReviewRepository extends JpaRepository<FeedbackReview, Long> {
    // TODO: add custom queries if needed
}
