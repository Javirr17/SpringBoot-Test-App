package com.example.test.controller.http;


import com.example.test.models.Pagination;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllUsersResponse<T>{

    private T data;
    private Pagination pagination;
}

