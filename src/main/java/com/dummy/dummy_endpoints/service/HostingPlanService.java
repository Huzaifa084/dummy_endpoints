package com.dummy.dummy_endpoints.service;

import com.dummy.dummy_endpoints.dto.HostingPlanCreateDto;
import com.dummy.dummy_endpoints.dto.HostingPlanDto;
import com.dummy.dummy_endpoints.dto.HostingPlanUpdateDto;

import java.util.List;

public interface HostingPlanService {
    List<HostingPlanDto> getAllHostingPlans();
    HostingPlanDto getHostingPlanById(Long id);
    HostingPlanDto createHostingPlan(HostingPlanCreateDto hostingPlanCreateDto);
    HostingPlanDto updateHostingPlan(Long id, HostingPlanUpdateDto hostingPlanUpdateDto);
    void deleteHostingPlan(Long id);
}
