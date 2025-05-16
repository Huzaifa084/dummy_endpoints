package com.dummy.dummy_endpoints.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hosting_plan")
public class HostingPlan extends BaseModel {
    private String planName;
    private Double pricePerMonth;
    private String space;
    private String bandwidth;
    private Boolean isFreeDomain;
    private String databaseMemory;
}
