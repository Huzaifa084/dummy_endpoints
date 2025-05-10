package com.dummy.dummy_endpoints.dto;

import com.dummy.dummy_endpoints.model.enums.CardPriority;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto {
    private Long id;
    private String title;
    private String description;
    private Boolean isActive;
    private CardPriority priority;
    private Long categoryId;
    private String categoryName;
    private Instant createdAt;
    private Instant updatedAt;
}