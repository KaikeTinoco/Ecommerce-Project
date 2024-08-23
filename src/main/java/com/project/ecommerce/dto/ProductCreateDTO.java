package com.project.ecommerce.dto;

import com.project.ecommerce.enums.ProductClassification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDTO {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private ProductClassification productClassification;
}

