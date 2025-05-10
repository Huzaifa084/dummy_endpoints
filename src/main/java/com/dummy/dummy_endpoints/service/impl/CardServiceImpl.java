package com.dummy.dummy_endpoints.service.impl;

import com.dummy.dummy_endpoints.dto.*;
import com.dummy.dummy_endpoints.exception.ResourceNotFoundException;
import com.dummy.dummy_endpoints.mapper.CardMapper;
import com.dummy.dummy_endpoints.model.Card;
import com.dummy.dummy_endpoints.model.CardCategory;
import com.dummy.dummy_endpoints.repository.CardCategoryRepository;
import com.dummy.dummy_endpoints.repository.CardRepository;
import com.dummy.dummy_endpoints.service.CardService;
import com.dummy.dummy_endpoints.specifications.CardSpecifications;
import com.dummy.dummy_endpoints.util.PagedResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardCategoryRepository categoryRepository;
    private final CardMapper cardMapper;

    @Override
    public PagedResponse<CardDto> getCards(int page, int size, String[] sort) {
        Pageable pageable = createPageable(page, size, sort);
        Page<Card> cardsPage = cardRepository.findAll(pageable);
        return PagedResponseUtil.createPagedResponse(cardsPage, cardsPage.getContent().stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList()));
    }

    @Override
    public PagedResponse<CardDto> getCardsByFilter(CardFilterDto filterDto) {
        Pageable pageable = createPageable(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() : 10,
                filterDto.getSort()
        );

        Page<Card> cardsPage = cardRepository.findAll(CardSpecifications.withFilters(filterDto), pageable);

        return PagedResponseUtil.createPagedResponse(cardsPage, cardsPage.getContent().stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList()));
    }

    @Override
    public CardDto getCardById(Long id) {
        Card card = findCardById(id);
        return cardMapper.toDto(card);
    }

    @Override
    public List<CardDto> getActiveCardsBetweenDates(Instant startDate, Instant endDate) {
        return cardRepository.findActiveCardsBetweenDates(startDate, endDate).stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CardDto createCard(CardCreateDto cardCreateDto) {
        CardCategory category = null;
        if (cardCreateDto.getCategoryId() != null) {
            category = categoryRepository.findById(cardCreateDto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + cardCreateDto.getCategoryId()));
        }

        Card card = cardMapper.toEntity(cardCreateDto, category);
        Card savedCard = cardRepository.save(card);
        return cardMapper.toDto(savedCard);
    }

    @Override
    @Transactional
    public List<CardDto> createCards(BulkCardCreateDto bulkCardCreateDto) {
        List<Card> cardsToSave = new ArrayList<>();

        for (CardCreateDto dto : bulkCardCreateDto.getCards()) {
            CardCategory category = null;
            if (dto.getCategoryId() != null) {
                category = categoryRepository.findById(dto.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategoryId()));
            }

            Card card = cardMapper.toEntity(dto, category);
            cardsToSave.add(card);
        }

        List<Card> savedCards = cardRepository.saveAll(cardsToSave);
        return savedCards.stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CardDto updateCard(Long id, CardUpdateDto cardUpdateDto) {
        Card card = findCardById(id);

        CardCategory category = null;
        if (cardUpdateDto.getCategoryId() != null) {
            category = categoryRepository.findById(cardUpdateDto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + cardUpdateDto.getCategoryId()));
        }

        cardMapper.updateEntityFromDto(card, cardUpdateDto, category);
        Card updatedCard = cardRepository.save(card);
        return cardMapper.toDto(updatedCard);
    }

    @Override
    @Transactional
    public void deleteCard(Long id) {
        if (!cardRepository.existsById(id)) {
            throw new ResourceNotFoundException("Card not found with id: " + id);
        }
        cardRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteCards(List<Long> ids) {
        List<Card> cardsToDelete = cardRepository.findAllById(ids);
        if (cardsToDelete.size() != ids.size()) {
            throw new ResourceNotFoundException("One or more cards not found");
        }
        cardRepository.deleteAllById(ids);
    }

    @Override
    public PagedResponse<CardDto> searchCards(String keyword, Pageable pageable) {
        Page<Card> titleResults = cardRepository.findByTitleContainingIgnoreCase(keyword, pageable);

        return PagedResponseUtil.createPagedResponse(titleResults, titleResults.getContent().stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList()));
    }

    private Card findCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + id));
    }

    private Pageable createPageable(int page, int size, String[] sort) {
        if (sort == null || sort.length == 0) {
            return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        }

        List<Sort.Order> orders = new ArrayList<>();
        for (String s : sort) {
            String[] parts = s.split(":");
            String field = parts[0];
            Sort.Direction direction = parts.length > 1 ?
                    ("desc".equalsIgnoreCase(parts[1]) ? Sort.Direction.DESC : Sort.Direction.ASC) :
                    Sort.Direction.ASC;
            orders.add(new Sort.Order(direction, field));
        }

        return PageRequest.of(page, size, Sort.by(orders));
    }
}