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
public class FAQDto {
    private Long id;
    private String question;
    private String answer;
    private Instant createdAt;
    private Instant updatedAt;
}