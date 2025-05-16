package com.dummy.dummy_endpoints.service.impl;

import com.dummy.dummy_endpoints.dto.FAQCreateDto;
import com.dummy.dummy_endpoints.dto.FAQDto;
import com.dummy.dummy_endpoints.dto.FAQUpdateDto;
import com.dummy.dummy_endpoints.exception.ResourceNotFoundException;
import com.dummy.dummy_endpoints.mapper.FAQMapper;
import com.dummy.dummy_endpoints.model.FAQ;
import com.dummy.dummy_endpoints.repository.FAQRepository;
import com.dummy.dummy_endpoints.service.FAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

    private final FAQRepository faqRepository;
    private final FAQMapper faqMapper;

    @Override
    public List<FAQDto> getAllFAQs() {
        return faqRepository.findAll().stream()
                .map(faqMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FAQDto getFAQById(Long id) {
        return faqRepository.findById(id)
                .map(faqMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("FAQ not found with id: " + id));
    }

    @Override
    public FAQDto createFAQ(FAQCreateDto faqCreateDto) {
        FAQ faq = faqMapper.toEntity(faqCreateDto);
        FAQ savedFAQ = faqRepository.save(faq);
        return faqMapper.toDto(savedFAQ);
    }

    @Override
    public FAQDto updateFAQ(Long id, FAQUpdateDto faqUpdateDto) {
        FAQ faq = faqRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FAQ not found with id: " + id));

        faqMapper.updateEntityFromDto(faq, faqUpdateDto);
        FAQ updatedFAQ = faqRepository.save(faq);
        return faqMapper.toDto(updatedFAQ);
    }

    @Override
    public void deleteFAQ(Long id) {
        if (!faqRepository.existsById(id))
            throw new ResourceNotFoundException("FAQ not found with id: " + id);
        faqRepository.deleteById(id);
    }

    @Override
    public Page<FAQDto> getPaginatedFAQs(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<FAQ> faqPage = faqRepository.findAll(pageRequest);
        return faqPage.map(faqMapper::toDto);
    }

    @Override
    public List<FAQDto> createFAQsBatch(List<FAQCreateDto> faqCreateDtos) {
        List<FAQ> faqs = faqCreateDtos.stream()
                .map(faqMapper::toEntity)
                .collect(Collectors.toList());
        List<FAQ> savedFaqs = faqRepository.saveAll(faqs);
        return savedFaqs.stream()
                .map(faqMapper::toDto)
                .collect(Collectors.toList());
    }
}