package com.dummy.dummy_endpoints.service.impl;

import com.dummy.dummy_endpoints.dto.CardCategoryCreateDto;
import com.dummy.dummy_endpoints.dto.CardCategoryDto;
import com.dummy.dummy_endpoints.dto.CardCategoryUpdateDto;
import com.dummy.dummy_endpoints.dto.PagedResponse;
import com.dummy.dummy_endpoints.exception.ResourceNotFoundException;
import com.dummy.dummy_endpoints.mapper.CardCategoryMapper;
import com.dummy.dummy_endpoints.model.CardCategory;
import com.dummy.dummy_endpoints.repository.CardCategoryRepository;
import com.dummy.dummy_endpoints.service.CardCategoryService;
import com.dummy.dummy_endpoints.specifications.CardCategorySpecifications;
import com.dummy.dummy_endpoints.util.PagedResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardCategoryServiceImpl implements CardCategoryService {

    private final CardCategoryRepository categoryRepository;
    private final CardCategoryMapper categoryMapper;

    @Override
    public PagedResponse<CardCategoryDto> getCardCategories(int page, int size, String[] sort) {
        Pageable pageable = createPageable(page, size, sort);
        Page<CardCategory> categoriesPage = categoryRepository.findAll(pageable);
        return PagedResponseUtil.createPagedResponse(categoriesPage, categoriesPage.getContent().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList()));
    }

    @Override
    public CardCategoryDto getCardCategoryById(Long id) {
        CardCategory category = findCardCategoryById(id);
        return categoryMapper.toDto(category);
    }

    @Override
    public CardCategoryDto getCardCategoryWithCards(Long id) {
        CardCategory category = findCardCategoryById(id);
        return categoryMapper.toDtoWithCards(category);
    }

    @Override
    public List<CardCategoryDto> getAllActiveCategories() {
        return categoryRepository.findAll()
                .stream()
                .filter(CardCategory::getIsActive)
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CardCategoryDto createCardCategory(CardCategoryCreateDto categoryCreateDto) {
        CardCategory category = categoryMapper.toEntity(categoryCreateDto);
        CardCategory savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    @Transactional
    public CardCategoryDto updateCardCategory(Long id, CardCategoryUpdateDto categoryUpdateDto) {
        CardCategory category = findCardCategoryById(id);
        categoryMapper.updateEntityFromDto(category, categoryUpdateDto);
        CardCategory updatedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(updatedCategory);
    }

    @Override
    @Transactional
    public void deleteCardCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("CardCategory not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public PagedResponse<CardCategoryDto> searchCardCategories(String name, String description, Boolean isActive, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));

        Specification<CardCategory> spec = Specification.where(null);

        if (StringUtils.hasText(name)) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }

        if (StringUtils.hasText(description)) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + description.toLowerCase() + "%"));
        }

        if (isActive != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), isActive));
        }

        Page<CardCategory> categoriesPage = categoryRepository.findAll(spec, pageable);

        return PagedResponseUtil.createPagedResponse(categoriesPage, categoriesPage.getContent().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList()));
    }

    private CardCategory findCardCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CardCategory not found with id: " + id));
    }

    private Pageable createPageable(int page, int size, String[] sort) {
        if (sort == null || sort.length == 0) {
            return PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
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