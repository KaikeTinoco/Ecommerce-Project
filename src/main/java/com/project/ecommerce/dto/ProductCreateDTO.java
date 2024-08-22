package com.project.ecommerce.dto;

import com.project.ecommerce.enums.ProductClassification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDTO {
    String productName;
    String productDescription;
    BigDecimal productPrice;
    ProductClassification productClassification;
}

