package com.dummy.dummy_endpoints.controller;

import com.dummy.dummy_endpoints.dto.ReviewCreateDto;
import com.dummy.dummy_endpoints.dto.ReviewDto;
import com.dummy.dummy_endpoints.dto.ReviewUpdateDto;
import com.dummy.dummy_endpoints.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Operation(summary = "Delete a review")
    public ResponseEntity<Map<String, String>> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Review deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/batch")
    @Operation(summary = "Create multiple reviews in batch")
    public ResponseEntity<List<ReviewDto>> createReviewsBatch(@RequestBody List<ReviewCreateDto> reviewCreateDtos) {
        List<ReviewDto> createdReviews = reviewService.createReviewsBatch(reviewCreateDtos);
        return new ResponseEntity<>(createdReviews, HttpStatus.CREATED);
    }

    @GetMapping("/paginated")
    @Operation(summary = "Get paginated reviews")
    public ResponseEntity<Page<ReviewDto>> getPaginatedReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<ReviewDto> paginatedReviews = reviewService.getPaginatedReviews(page, size);
        return ResponseEntity.ok(paginatedReviews);
    }
}