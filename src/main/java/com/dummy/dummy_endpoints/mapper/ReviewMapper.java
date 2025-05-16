package com.dummy.dummy_endpoints.mapper;

import com.dummy.dummy_endpoints.dto.ReviewCreateDto;
import com.dummy.dummy_endpoints.dto.ReviewDto;
import com.dummy.dummy_endpoints.dto.ReviewUpdateDto;
import com.dummy.dummy_endpoints.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewDto toDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .reviewContent(review.getReviewContent())
                .rating(review.getRating())
                .reviewerName(review.getReviewerName())
                .reviewerCategory(review.getReviewerCategory())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

    public Review toEntity(ReviewCreateDto dto) {
        return Review.builder()
                .reviewContent(dto.getReviewContent())
                .rating(dto.getRating())
                .reviewerName(dto.getReviewerName())
                .reviewerCategory(dto.getReviewerCategory())
                .build();
    }

    public void updateEntityFromDto(Review review, ReviewUpdateDto dto) {
        if (dto.getReviewContent() != null) {
            review.setReviewContent(dto.getReviewContent());
        }
        review.setRating(dto.getRating());
        if (dto.getReviewerName() != null) {
            review.setReviewerName(dto.getReviewerName());
        }
        if (dto.getReviewerCategory() != null) {
            review.setReviewerCategory(dto.getReviewerCategory());
        }
    }
}
