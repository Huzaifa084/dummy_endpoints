package com.dummy.dummy_endpoints.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HostingPlanCreateDto {
    private String planName;
    private Double pricePerMonth;
    private String space;
    private String bandwidth;
    private Boolean isFreeDomain;
    private String database;
}
