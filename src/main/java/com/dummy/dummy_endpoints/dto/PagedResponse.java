package com.dummy.dummy_endpoints.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<T> {
    private List<T> content;
    private PageInfo pageInfo;

    public PagedResponse(List<T> content, int pageNumber, int pageSize, long totalElements,
                         int totalPages, boolean isLast) {
        this.content = content;
        this.pageInfo = PageInfo.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .isLast(isLast)
                .isFirst(pageNumber == 0)
                .hasNext(!isLast)
                .hasPrevious(pageNumber > 0)
                .build();
    }
}