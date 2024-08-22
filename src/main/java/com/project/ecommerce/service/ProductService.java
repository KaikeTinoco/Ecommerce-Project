package com.project.ecommerce.service;

import com.project.ecommerce.dto.ProductCreateDTO;
import com.project.ecommerce.model.ProductModel;
import com.project.ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;



    public ResponseEntity<ProductModel> registerProduct(ProductCreateDTO dto) {
        try {
            ProductModel newProduct = new ProductModel();
            newProduct.setProductName(dto.getProductName());
            newProduct.setProductDescription(dto.getProductDescription());
            newProduct.setProductPrice(dto.getProductPrice());
            newProduct.setProductClassification(dto.getProductClassification());
            newProduct.setProductId(setProductID());
            productRepository.save(newProduct);
            return ResponseEntity.ok(newProduct);
        } catch (Error erro) {
            throw new RuntimeException("erro aconteceu, ainda não fiz o handler kkkkkk");
        }

    }


    private Long setProductID(){
        List<ProductModel> list = productRepository.findAll();
        if (list.isEmpty()){
            return Long.valueOf(1);
        } else {
            return Long.valueOf(list.size() + 1);
        }
    }


    public ResponseEntity<List<ProductModel>> findAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    public ResponseEntity<ProductModel> changeProduct(Long id,
                                                      ProductCreateDTO dto) {
        try {
            ProductModel product = productRepository.findByProductId(id).get();
            if(dto.getProductName() != null || dto.getProductName() != ""){
                product.setProductName(dto.getProductName());
            }
            if(dto.getProductDescription() != null || dto.getProductDescription() != ""){
                product.setProductDescription(dto.getProductDescription());
            }
            if(dto.getProductPrice() != null){
                product.setProductPrice(dto.getProductPrice());
            }
            if(dto.getProductClassification() != null){
                product.setProductClassification(dto.getProductClassification());
            }
            return ResponseEntity.ok(product);

        } catch (Exception e){
            throw e;
        }
    }
}
