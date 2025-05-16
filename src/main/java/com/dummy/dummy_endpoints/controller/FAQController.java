package com.dummy.dummy_endpoints.controller;

import com.dummy.dummy_endpoints.dto.FAQCreateDto;
import com.dummy.dummy_endpoints.dto.FAQDto;
import com.dummy.dummy_endpoints.dto.FAQUpdateDto;
import com.dummy.dummy_endpoints.service.FAQService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/faqs")
@RequiredArgsConstructor
public class FAQController {
    private final FAQService faqService;

    @GetMapping("/paginated")
    @Operation(summary = "Get paginated FAQs")
    public ResponseEntity<Page<FAQDto>> getPaginatedFAQs(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(faqService.getPaginatedFAQs(page, size));
    }

    @PostMapping("/batch")
    @Operation(summary = "Add multiple FAQs in batch")
    public ResponseEntity<List<FAQDto>> createFAQsBatch(@RequestBody List<FAQCreateDto> faqCreateDtos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.createFAQsBatch(faqCreateDtos));
    }

    @GetMapping
    @Operation(summary = "Get all FAQs")
    public ResponseEntity<List<FAQDto>> getAllFAQs() {
        return ResponseEntity.ok(faqService.getAllFAQs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FAQDto> getFAQById(@PathVariable Long id) {
        return ResponseEntity.ok(faqService.getFAQById(id));
    }

    @PostMapping
    public ResponseEntity<FAQDto> createFAQ(@RequestBody FAQCreateDto faqCreateDto) {
        return new ResponseEntity<>(faqService.createFAQ(faqCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FAQDto> updateFAQ(
            @PathVariable Long id,
            @RequestBody FAQUpdateDto faqUpdateDto) {
        return ResponseEntity.ok(faqService.updateFAQ(id, faqUpdateDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an FAQ")
    public ResponseEntity<Map<String, String>> deleteFAQ(@PathVariable Long id) {
        faqService.deleteFAQ(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "FAQ deleted successfully");
        return ResponseEntity.ok(response);
    }
}
