package com.dummy.dummy_endpoints.service;

import com.dummy.dummy_endpoints.dto.*;

import java.util.List;

public interface CardCategoryService {
    PagedResponse<CardCategoryDto> getCardCategories(int page, int size, String[] sort);

    CardCategoryDto getCardCategoryById(Long id);

    CardCategoryDto getCardCategoryWithCards(Long id);

    List<CardCategoryDto> getAllActiveCategories();

    CardCategoryDto createCardCategory(CardCategoryCreateDto categoryCreateDto);

    CardCategoryDto updateCardCategory(Long id, CardCategoryUpdateDto categoryUpdateDto);

    void deleteCardCategory(Long id);

    PagedResponse<CardCategoryDto> searchCardCategories(String name, String description, Boolean isActive, int page, int size);

    List<CardCategoryDto> createCardCategories(BulkCardCategoryCreateDto bulkCardCategoryCreateDto);
}
