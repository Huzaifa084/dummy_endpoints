package com.dummy.dummy_endpoints.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulkCardCategoryCreateDto {
    @NotEmpty(message = "Categories list cannot be empty")
    private List<@Valid CardCategoryCreateDto> categories;
}