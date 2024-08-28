package com.project.ecommerce.controller;

import com.project.ecommerce.dto.ProductCreateDTO;
import com.project.ecommerce.model.ProductModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product")
public interface ProductControllerOpenApi {
    @PostMapping(path = "/register")
    ResponseEntity<ProductModel> registerProduct(@Valid @RequestBody ProductCreateDTO dto);

    @GetMapping(path = "/findAllProducts")
     ResponseEntity<List<ProductModel>> findAllProducts();

    @PutMapping(path = "/changeProduct/{productId}")
     ResponseEntity<ProductModel> changeProduct(@Valid @PathVariable Long productId,
                                                      @Valid @RequestBody(required = false)ProductCreateDTO dto);
    @DeleteMapping(path = "/delete/{productId}")
     ResponseEntity<String> deleteProduct(@Valid @PathVariable Long productId);

    @GetMapping(path = "/search")
     ResponseEntity<List<ProductModel>> searchQueryProducts(
            @RequestParam(required = false)String productName,
            @RequestParam(required = false)Double maxPrice,
            @RequestParam(required = false)Double minPrice);
}
