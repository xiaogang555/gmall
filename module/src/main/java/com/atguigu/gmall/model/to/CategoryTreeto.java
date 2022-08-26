package com.atguigu.gmall.model.to;

import lombok.Data;

import java.util.List;

@Data
public class CategoryTreeto {
    private Long categoryId;
    private String categoryName;
    private List<CategoryTreeto> categoryChild;
}
