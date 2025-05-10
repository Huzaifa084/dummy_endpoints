package com.dummy.dummy_endpoints.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardCategoryDto {
    private Long id;
    private String name;
    private String description;
    private Boolean isActive;
    private Integer cardCount;
    private List<CardDto> cards;
    private Instant createdAt;
    private Instant updatedAt;
}
