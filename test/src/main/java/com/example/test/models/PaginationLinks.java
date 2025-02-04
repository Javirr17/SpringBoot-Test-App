package com.example.test.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationLinks {

    private String first;
    private String prev;
    private String self;
    private String next;
    private String last;

}
