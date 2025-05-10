package com.dummy.dummy_endpoints.mapper;

import com.dummy.dummy_endpoints.dto.CardCreateDto;
import com.dummy.dummy_endpoints.dto.CardDto;
import com.dummy.dummy_endpoints.dto.CardUpdateDto;
import com.dummy.dummy_endpoints.model.Card;
import com.dummy.dummy_endpoints.model.CardCategory;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDto toDto(Card card) {
        CardDto dto = CardDto.builder()
                .id(card.getId())
                .title(card.getTitle())
                .description(card.getDescription())
                .isActive(card.getIsActive())
                .priority(card.getPriority())
                .createdAt(card.getCreatedAt())
                .updatedAt(card.getUpdatedAt())
                .build();

        if (card.getCategory() != null) {
            dto.setCategoryId(card.getCategory().getId());
            dto.setCategoryName(card.getCategory().getName());
        }

        return dto;
    }

    public Card toEntity(CardCreateDto dto, CardCategory category) {
        return Card.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .isActive(dto.getIsActive())
                .priority(dto.getPriority())
                .category(category)
                .build();
    }

    public void updateEntityFromDto(Card card, CardUpdateDto dto, CardCategory category) {
        if (dto.getTitle() != null) {
            card.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            card.setDescription(dto.getDescription());
        }
        if (dto.getIsActive() != null) {
            card.setIsActive(dto.getIsActive());
        }
        if (dto.getPriority() != null) {
            card.setPriority(dto.getPriority());
        }
        if (category != null) {
            card.setCategory(category);
        }
    }
}