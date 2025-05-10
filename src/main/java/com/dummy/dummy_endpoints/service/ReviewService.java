package com.dummy.dummy_endpoints.service;

import com.dummy.dummy_endpoints.dto.ReviewCreateDto;
import com.dummy.dummy_endpoints.dto.ReviewDto;
import com.dummy.dummy_endpoints.dto.ReviewUpdateDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAllReviews();
    ReviewDto getReviewById(Long id);
    ReviewDto createReview(ReviewCreateDto reviewCreateDto);
    ReviewDto updateReview(Long id, ReviewUpdateDto reviewUpdateDto);
    void deleteReview(Long id);
}
