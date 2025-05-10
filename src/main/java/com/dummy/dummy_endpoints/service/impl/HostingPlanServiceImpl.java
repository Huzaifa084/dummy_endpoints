package com.dummy.dummy_endpoints.service.impl;

import com.dummy.dummy_endpoints.dto.HostingPlanCreateDto;
import com.dummy.dummy_endpoints.dto.HostingPlanDto;
import com.dummy.dummy_endpoints.dto.HostingPlanUpdateDto;
import com.dummy.dummy_endpoints.exception.ResourceNotFoundException;
import com.dummy.dummy_endpoints.mapper.HostingPlanMapper;
import com.dummy.dummy_endpoints.model.HostingPlan;
import com.dummy.dummy_endpoints.repository.HostingPlanRepository;
import com.dummy.dummy_endpoints.service.HostingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostingPlanServiceImpl implements HostingPlanService {

    private final HostingPlanRepository hostingPlanRepository;
    private final HostingPlanMapper hostingPlanMapper;

    @Override
    public List<HostingPlanDto> getAllHostingPlans() {
        return hostingPlanRepository.findAll().stream()
                .map(hostingPlanMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HostingPlanDto getHostingPlanById(Long id) {
        return hostingPlanRepository.findById(id)
                .map(hostingPlanMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("HostingPlan not found with id: " + id));
    }

    @Override
    public HostingPlanDto createHostingPlan(HostingPlanCreateDto hostingPlanCreateDto) {
        HostingPlan hostingPlan = hostingPlanMapper.toEntity(hostingPlanCreateDto);
        HostingPlan savedHostingPlan = hostingPlanRepository.save(hostingPlan);
        return hostingPlanMapper.toDto(savedHostingPlan);
    }

    @Override
    public HostingPlanDto updateHostingPlan(Long id, HostingPlanUpdateDto hostingPlanUpdateDto) {
        HostingPlan hostingPlan = hostingPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HostingPlan not found with id: " + id));

        hostingPlanMapper.updateEntityFromDto(hostingPlan, hostingPlanUpdateDto);
        HostingPlan updatedHostingPlan = hostingPlanRepository.save(hostingPlan);
        return hostingPlanMapper.toDto(updatedHostingPlan);
    }

    @Override
    public void deleteHostingPlan(Long id) {
        if (!hostingPlanRepository.existsById(id)) {
            throw new ResourceNotFoundException("HostingPlan not found with id: " + id);
        }
        hostingPlanRepository.deleteById(id);
    }
}

