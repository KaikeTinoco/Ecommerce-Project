package com.project.ecommerce.model;

import com.project.ecommerce.enums.ProductClassification;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "productId não pode ser nulo!")
    private Long productId;

    @NotNull(message = "productName não pode ser nulo!")
    @NotBlank(message = "productName não pode ser vazio!")
    private String productName;

    @NotNull(message = "productDescription não pode ser nulo!")
    @NotBlank(message = "productDescription não pode ser vazio!")
    private String productDescription;

    @NotNull(message = "productPrice não pode ser nulo!")
    private Double productPrice;

    @NotNull(message = "productClassification não pode ser nulo!")
    private ProductClassification productClassification;

}


