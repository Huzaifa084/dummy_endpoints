package com.dummy.dummy_endpoints.service;

import com.dummy.dummy_endpoints.dto.*;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public interface CardService {
    PagedResponse<CardDto> getCards(int page, int size, String[] sort);

    PagedResponse<CardDto> getCardsByFilter(CardFilterDto filterDto);

    CardDto getCardById(Long id);

    List<CardDto> getActiveCardsBetweenDates(Instant startDate, Instant endDate);

    CardDto createCard(CardCreateDto cardCreateDto);

    List<CardDto> createCards(BulkCardCreateDto bulkCardCreateDto);

    CardDto updateCard(Long id, CardUpdateDto cardUpdateDto);

    void deleteCard(Long id);

    void deleteCards(List<Long> ids);

    PagedResponse<CardDto> searchCards(String keyword, Pageable pageable);
}