package com.dummy.dummy_endpoints.repository;

import com.dummy.dummy_endpoints.model.HostingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostingPlanRepository extends JpaRepository<HostingPlan, Long> {
}
