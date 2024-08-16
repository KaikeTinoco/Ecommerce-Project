package com.project.ecommerce.model;

import com.project.ecommerce.enums.ProductClassification;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private ProductClassification productClassification;

}
