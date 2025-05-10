package com.dummy.dummy_endpoints.mapper;

import com.dummy.dummy_endpoints.dto.HostingPlanCreateDto;
import com.dummy.dummy_endpoints.dto.HostingPlanDto;
import com.dummy.dummy_endpoints.dto.HostingPlanUpdateDto;
import com.dummy.dummy_endpoints.model.HostingPlan;
import org.springframework.stereotype.Component;

@Component
public class HostingPlanMapper {

    public HostingPlanDto toDto(HostingPlan hostingPlan) {
        return HostingPlanDto.builder()
                .id(hostingPlan.getId())
                .planName(hostingPlan.getPlanName())
                .pricePerMonth(hostingPlan.getPricePerMonth())
                .space(hostingPlan.getSpace())
                .bandwidth(hostingPlan.getBandwidth())
                .isFreeDomain(hostingPlan.getIsFreeDomain())
                .database(hostingPlan.getDatabaseMemory())
                .createdAt(hostingPlan.getCreatedAt())
                .updatedAt(hostingPlan.getUpdatedAt())
                .build();
    }

    public HostingPlan toEntity(HostingPlanCreateDto dto) {
        return HostingPlan.builder()
                .planName(dto.getPlanName())
                .pricePerMonth(dto.getPricePerMonth())
                .space(dto.getSpace())
                .bandwidth(dto.getBandwidth())
                .isFreeDomain(dto.getIsFreeDomain())
                .databaseMemory(dto.getDatabase())
                .build();
    }

    public void updateEntityFromDto(HostingPlan hostingPlan, HostingPlanUpdateDto dto) {
        if (dto.getPlanName() != null) {
            hostingPlan.setPlanName(dto.getPlanName());
        }
        if (dto.getPricePerMonth() != null) {
            hostingPlan.setPricePerMonth(dto.getPricePerMonth());
        }
        if (dto.getSpace() != null) {
            hostingPlan.setSpace(dto.getSpace());
        }
        if (dto.getBandwidth() != null) {
            hostingPlan.setBandwidth(dto.getBandwidth());
        }
        if (dto.getIsFreeDomain() != null) {
            hostingPlan.setIsFreeDomain(dto.getIsFreeDomain());
        }
        if (dto.getDatabase() != null) {
            hostingPlan.setDatabaseMemory(dto.getDatabase());
        }
    }
}