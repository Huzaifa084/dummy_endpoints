package com.dummy.dummy_endpoints.controller;

import com.dummy.dummy_endpoints.dto.*;
import com.dummy.dummy_endpoints.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    @Operation(summary = "Get all cards")
    public ResponseEntity<PagedResponse<CardDto>> getCards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String[] sort) {
        return ResponseEntity.ok(cardService.getCards(page, size, sort));
    }

    @GetMapping("/filter")
    @Operation(summary = "Get all cards filtered by given filter")
    public ResponseEntity<PagedResponse<CardDto>> getCardsByFilter(
            @ModelAttribute CardFilterDto filterDto) {
        return ResponseEntity.ok(cardService.getCardsByFilter(filterDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get card by ID")
    public ResponseEntity<CardDto> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @GetMapping("/active-between")
    @Operation(summary = "Get all active cards between two dates")
    public ResponseEntity<List<CardDto>> getActiveCardsBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String startDateStr,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String endDateStr) {

        Instant startDate = LocalDate.parse(startDateStr).atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant();

        return ResponseEntity.ok(cardService.getActiveCardsBetweenDates(startDate, endDate));
    }

    @PostMapping
    @Operation(summary = "Create a new card")
    public ResponseEntity<CardDto> createCard(@Valid @RequestBody CardCreateDto cardCreateDto) {
        return new ResponseEntity<>(cardService.createCard(cardCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple cards")
    public ResponseEntity<List<CardDto>> createCards(@Valid @RequestBody BulkCardCreateDto bulkCardCreateDto) {
        return new ResponseEntity<>(cardService.createCards(bulkCardCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a card")
    public ResponseEntity<CardDto> updateCard(
            @PathVariable Long id,
            @Valid @RequestBody CardUpdateDto cardUpdateDto) {
        return ResponseEntity.ok(cardService.updateCard(id, cardUpdateDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a card by ID")
    public ResponseEntity<Map<String, String>> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Card deleted successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/bulk")
    @Operation(summary = "Delete multiple cards by IDs")
    public ResponseEntity<Map<String, String>> deleteCards(@RequestBody DeleteCardsDro ids) {
        cardService.deleteCards(ids.getIds());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cards deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(summary = "Search cards by keyword")
    public ResponseEntity<PagedResponse<CardDto>> searchCards(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(cardService.searchCards(keyword,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"))));
    }
}