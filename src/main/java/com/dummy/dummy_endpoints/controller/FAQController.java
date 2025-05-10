package com.dummy.dummy_endpoints.controller;

import com.dummy.dummy_endpoints.dto.FAQCreateDto;
import com.dummy.dummy_endpoints.dto.FAQDto;
import com.dummy.dummy_endpoints.dto.FAQUpdateDto;
import com.dummy.dummy_endpoints.service.FAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
@RequiredArgsConstructor
public class FAQController {

    private final FAQService faqService;

    @GetMapping
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
    public ResponseEntity<Void> deleteFAQ(@PathVariable Long id) {
        faqService.deleteFAQ(id);
        return ResponseEntity.noContent().build();
    }
}
