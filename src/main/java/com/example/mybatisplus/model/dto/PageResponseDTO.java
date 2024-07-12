package com.example.mybatisplus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO<T> {
    private List<T> data;
    private Long total;
}
