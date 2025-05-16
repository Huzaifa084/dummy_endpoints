package com.dummy.dummy_endpoints.service;

import com.dummy.dummy_endpoints.dto.ReviewCreateDto;
import com.dummy.dummy_endpoints.dto.ReviewDto;
import com.dummy.dummy_endpoints.dto.ReviewUpdateDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAllReviews();

    ReviewDto getReviewById(Long id);

    ReviewDto createReview(ReviewCreateDto reviewCreateDto);

    ReviewDto updateReview(Long id, ReviewUpdateDto reviewUpdateDto);

    void deleteReview(Long id);

    List<ReviewDto> createReviewsBatch(List<ReviewCreateDto> reviewCreateDtos);

    Page<ReviewDto> getPaginatedReviews(int page, int size);
}
