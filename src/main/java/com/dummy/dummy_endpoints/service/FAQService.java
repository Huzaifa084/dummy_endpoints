package com.dummy.dummy_endpoints.service;

import com.dummy.dummy_endpoints.dto.FAQCreateDto;
import com.dummy.dummy_endpoints.dto.FAQDto;
import com.dummy.dummy_endpoints.dto.FAQUpdateDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FAQService {
    List<FAQDto> getAllFAQs();

    FAQDto getFAQById(Long id);

    FAQDto createFAQ(FAQCreateDto faqCreateDto);

    FAQDto updateFAQ(Long id, FAQUpdateDto faqUpdateDto);

    void deleteFAQ(Long id);

    List<FAQDto> createFAQsBatch(List<FAQCreateDto> faqCreateDtos);

    Page<FAQDto> getPaginatedFAQs(int page, int size);
}