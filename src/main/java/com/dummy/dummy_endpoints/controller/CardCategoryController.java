package com.dummy.dummy_endpoints.controller;

import com.dummy.dummy_endpoints.dto.CardCategoryCreateDto;
import com.dummy.dummy_endpoints.dto.CardCategoryDto;
import com.dummy.dummy_endpoints.dto.CardCategoryUpdateDto;
import com.dummy.dummy_endpoints.dto.PagedResponse;
import com.dummy.dummy_endpoints.service.CardCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card-categories")
@RequiredArgsConstructor
public class CardCategoryController {

    private final CardCategoryService cardCategoryService;

    @GetMapping
    @Operation(summary = "Get all card categories")
    public ResponseEntity<PagedResponse<CardCategoryDto>> getCardCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String[] sort) {
        return ResponseEntity.ok(cardCategoryService.getCardCategories(page, size, sort));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get card category by ID")
    public ResponseEntity<CardCategoryDto> getCardCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(cardCategoryService.getCardCategoryById(id));
    }

    @GetMapping("/{id}/with-cards")
    @Operation(summary = "Get card category with cards by ID")
    public ResponseEntity<CardCategoryDto> getCardCategoryWithCards(@PathVariable Long id) {
        return ResponseEntity.ok(cardCategoryService.getCardCategoryWithCards(id));
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active card categories")
    public ResponseEntity<List<CardCategoryDto>> getAllActiveCategories() {
        return ResponseEntity.ok(cardCategoryService.getAllActiveCategories());
    }

    @PostMapping
    @Operation(summary = "Create a new card category")
    public ResponseEntity<CardCategoryDto> createCardCategory(
            @Valid @RequestBody CardCategoryCreateDto categoryCreateDto) {
        return new ResponseEntity<>(cardCategoryService.createCardCategory(categoryCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing card category")
    public ResponseEntity<CardCategoryDto> updateCardCategory(
            @PathVariable Long id,
            @Valid @RequestBody CardCategoryUpdateDto categoryUpdateDto) {
        return ResponseEntity.ok(cardCategoryService.updateCardCategory(id, categoryUpdateDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a card category")
    public ResponseEntity<Void> deleteCardCategory(@PathVariable Long id) {
        cardCategoryService.deleteCardCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search card categories by name, description, and isActive status")
    public ResponseEntity<PagedResponse<CardCategoryDto>> searchCardCategories(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(cardCategoryService.searchCardCategories(name, description, isActive, page, size));
    }
}