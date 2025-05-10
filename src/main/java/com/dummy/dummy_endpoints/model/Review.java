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
@Table(name = "review")
public class Review extends BaseModel {
    private String reviewContent;
    private int rating;
    private String reviewerName;
    private String reviewerCategory;
}
