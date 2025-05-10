package com.dummy.dummy_endpoints.mapper;

import com.dummy.dummy_endpoints.dto.CardCategoryCreateDto;
import com.dummy.dummy_endpoints.dto.CardCategoryDto;
import com.dummy.dummy_endpoints.dto.CardCategoryUpdateDto;
import com.dummy.dummy_endpoints.dto.CardDto;
import com.dummy.dummy_endpoints.model.CardCategory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardCategoryMapper {

    private final CardMapper cardMapper;

    public CardCategoryMapper(CardMapper cardMapper) {
        this.cardMapper = cardMapper;
    }

    public CardCategoryDto toDto(CardCategory category) {
        return CardCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .isActive(category.getIsActive())
                .cardCount(category.getCards() != null ? category.getCards().size() : 0)
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

    public CardCategoryDto toDtoWithCards(CardCategory category) {
        CardCategoryDto dto = toDto(category);

        if (category.getCards() != null && !category.getCards().isEmpty()) {
            List<CardDto> cardDtos = category.getCards().stream()
                    .map(cardMapper::toDto)
                    .collect(Collectors.toList());
            dto.setCards(cardDtos);
        } else {
            dto.setCards(Collections.emptyList());
        }

        return dto;
    }

    public CardCategory toEntity(CardCategoryCreateDto dto) {
        return CardCategory.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .isActive(dto.getIsActive())
                .build();
    }

    public void updateEntityFromDto(CardCategory category, CardCategoryUpdateDto dto) {
        if (dto.getName() != null) {
            category.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            category.setDescription(dto.getDescription());
        }
        if (dto.getIsActive() != null) {
            category.setIsActive(dto.getIsActive());
        }
    }
}