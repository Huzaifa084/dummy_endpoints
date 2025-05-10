package com.dummy.dummy_endpoints.mapper;

import com.dummy.dummy_endpoints.dto.FAQCreateDto;
import com.dummy.dummy_endpoints.dto.FAQDto;
import com.dummy.dummy_endpoints.dto.FAQUpdateDto;
import com.dummy.dummy_endpoints.model.FAQ;
import org.springframework.stereotype.Component;

@Component
public class FAQMapper {

    public FAQDto toDto(FAQ faq) {
        return FAQDto.builder()
                .id(faq.getId())
                .question(faq.getQuestion())
                .answer(faq.getAnswer())
                .createdAt(faq.getCreatedAt())
                .updatedAt(faq.getUpdatedAt())
                .build();
    }

    public FAQ toEntity(FAQCreateDto dto) {
        return FAQ.builder()
                .question(dto.getQuestion())
                .answer(dto.getAnswer())
                .build();
    }

    public void updateEntityFromDto(FAQ faq, FAQUpdateDto dto) {
        if (dto.getQuestion() != null) faq.setQuestion(dto.getQuestion());
        if (dto.getAnswer() != null) faq.setAnswer(dto.getAnswer());
    }
}
