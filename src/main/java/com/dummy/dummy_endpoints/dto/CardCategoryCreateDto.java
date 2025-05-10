package com.dummy.dummy_endpoints.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardCategoryCreateDto {
    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "isActive status is required")
    private Boolean isActive;
}