package com.dummy.dummy_endpoints.service.impl;

import com.dummy.dummy_endpoints.dto.ReviewCreateDto;
import com.dummy.dummy_endpoints.dto.ReviewDto;
import com.dummy.dummy_endpoints.dto.ReviewUpdateDto;
import com.dummy.dummy_endpoints.exception.ResourceNotFoundException;
import com.dummy.dummy_endpoints.mapper.ReviewMapper;
import com.dummy.dummy_endpoints.model.Review;
import com.dummy.dummy_endpoints.repository.ReviewRepository;
import com.dummy.dummy_endpoints.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
    }

    @Override
    public ReviewDto createReview(ReviewCreateDto reviewCreateDto) {
        Review review = reviewMapper.toEntity(reviewCreateDto);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }

    @Override
    public ReviewDto updateReview(Long id, ReviewUpdateDto reviewUpdateDto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));

        reviewMapper.updateEntityFromDto(review, reviewUpdateDto);
        Review updatedReview = reviewRepository.save(review);
        return reviewMapper.toDto(updatedReview);
    }

    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }
}