package com.dummy.dummy_endpoints.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardCategoryUpdateDto {
    private String name;
    private String description;
    private Boolean isActive;
}