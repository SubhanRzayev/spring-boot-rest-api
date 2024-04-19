package com.subhan.springbootrestapi.util;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
public class CustomPage<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private Sort sort;
    private int totalPage;
    private long totalElements;

    public CustomPage(Page page, List<T> content) {
        this.content = content;
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.sort = page.getSort();
        this.totalElements = page.getTotalElements();
        this.totalPage = page.getTotalPages();

    }
}
