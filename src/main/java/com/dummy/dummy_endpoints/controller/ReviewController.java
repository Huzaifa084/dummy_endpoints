package com.dummy.dummy_endpoints.controller;

import com.dummy.dummy_endpoints.dto.ReviewCreateDto;
import com.dummy.dummy_endpoints.dto.ReviewDto;
import com.dummy.dummy_endpoints.dto.ReviewUpdateDto;
import com.dummy.dummy_endpoints.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewCreateDto reviewCreateDto) {
        return new ResponseEntity<>(reviewService.createReview(reviewCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewUpdateDto reviewUpdateDto) {
        return ResponseEntity.ok(reviewService.updateReview(id, reviewUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}