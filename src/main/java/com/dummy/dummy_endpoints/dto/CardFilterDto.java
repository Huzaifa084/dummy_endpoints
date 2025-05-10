package com.dummy.dummy_endpoints.dto;

import com.dummy.dummy_endpoints.model.enums.CardPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardFilterDto {
    private String title;
    private String description;
    private Boolean isActive;
    private CardPriority priority;
    private Long categoryId;
    private Integer page;
    private Integer size;
    private String[] sort;
}