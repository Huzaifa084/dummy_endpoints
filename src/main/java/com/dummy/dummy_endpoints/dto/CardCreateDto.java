package com.dummy.dummy_endpoints.dto;

import com.dummy.dummy_endpoints.model.enums.CardPriority;
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
public class CardCreateDto {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "isActive status is required")
    private Boolean isActive;

    @NotNull(message = "Priority is required")
    private CardPriority priority;

    private Long categoryId;
}