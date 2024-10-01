package com.project.ecommerce.repositories;

import com.project.ecommerce.enums.ProductClassification;
import com.project.ecommerce.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long>, JpaSpecificationExecutor<ProductModel> {
    Optional<ProductModel> findByProductId(Long productId);

    Optional<List<ProductModel>> findByProductClassification(ProductClassification classification);
}
