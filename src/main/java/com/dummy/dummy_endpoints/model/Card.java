package com.dummy.dummy_endpoints.model;

import com.dummy.dummy_endpoints.model.enums.CardPriority;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cards")
public class Card extends BaseModel {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CardCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardPriority priority;
}
