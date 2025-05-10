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
public class HostingPlanDto {
    private Long id;
    private String planName;
    private Double pricePerMonth;
    private String space;
    private String bandwidth;
    private Boolean isFreeDomain;
    private String database;
    private Instant createdAt;
    private Instant updatedAt;
}