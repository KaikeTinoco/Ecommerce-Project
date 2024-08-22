package com.project.ecommerce.repositories;

import com.project.ecommerce.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel> findByProductId(Long productId);
}
