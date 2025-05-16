package com.dummy.dummy_endpoints.util;

import com.dummy.dummy_endpoints.dto.PagedResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class PagedResponseUtil {

    private PagedResponseUtil() {
        // Private constructor to prevent instantiation
    }

    public static <T> PagedResponse<T> createPagedResponse(Page<?> page, List<T> content) {
        return new PagedResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}