package com.project.ecommerce.controller;

import com.project.ecommerce.dto.ProductCreateDTO;
import com.project.ecommerce.enums.ProductClassification;
import com.project.ecommerce.model.ProductModel;
import com.project.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(path = "/register")
    public ResponseEntity<ProductModel> registerProduct(@RequestBody ProductCreateDTO dto){
        return productService.registerProduct(dto);

    }

    @GetMapping(path = "/findAllProducts")
    public ResponseEntity<List<ProductModel>> findAllProducts(){
        return productService.findAllProducts();
    }

    @PutMapping(path = "/changeProduct/{productId}")
    public ResponseEntity<ProductModel> changeProduct(@PathVariable Long productId,
                                                      @RequestBody(required = false)ProductCreateDTO dto){
        return productService.changeProduct(productId,dto);
    }


}
