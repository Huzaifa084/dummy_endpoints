package com.dummy.dummy_endpoints.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String reviewContent;
    private int rating;
    private String reviewerName;
    private String reviewerCategory;
    private Instant createdAt;
    private Instant updatedAt;
}
