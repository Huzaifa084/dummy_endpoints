package com.dummy.dummy_endpoints.controller;

import com.dummy.dummy_endpoints.dto.HostingPlanCreateDto;
import com.dummy.dummy_endpoints.dto.HostingPlanDto;
import com.dummy.dummy_endpoints.dto.HostingPlanUpdateDto;
import com.dummy.dummy_endpoints.service.HostingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class HostingPlanController {
    private final HostingPlanService hostingPlanService;

    @GetMapping
    public ResponseEntity<List<HostingPlanDto>> getAllHostingPlans() {
        return ResponseEntity.ok(hostingPlanService.getAllHostingPlans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostingPlanDto> getHostingPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(hostingPlanService.getHostingPlanById(id));
    }

    @PostMapping
    public ResponseEntity<HostingPlanDto> createHostingPlan(@RequestBody HostingPlanCreateDto hostingPlanCreateDto) {
        return new ResponseEntity<>(hostingPlanService.createHostingPlan(hostingPlanCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HostingPlanDto> updateHostingPlan(
            @PathVariable Long id,
            @RequestBody HostingPlanUpdateDto hostingPlanUpdateDto) {
        return ResponseEntity.ok(hostingPlanService.updateHostingPlan(id, hostingPlanUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHostingPlan(@PathVariable Long id) {
        hostingPlanService.deleteHostingPlan(id);
        return ResponseEntity.noContent().build();
    }
}