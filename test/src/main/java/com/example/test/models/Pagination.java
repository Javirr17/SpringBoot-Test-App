package com.example.test.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagination {

    private int offset;
    private int limit;
    private long totalElements;
    private int totalPages;
    private PaginationLinks links;
}