package com.project.ecommerce.service;

import com.project.ecommerce.dto.ProductCreateDTO;
import com.project.ecommerce.enums.ProductClassification;
import com.project.ecommerce.exception.BadRequestException;
import com.project.ecommerce.model.ProductModel;
import com.project.ecommerce.repositories.ProductRepository;
import com.project.ecommerce.specification.ProductSpecification;
import com.project.ecommerce.specification.criteria.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
        } catch (BadRequestException e) {
            throw new BadRequestException("erro ao tentar salvar o produto, por favor contate o suporte");
        }

    }


    private Long setProductID(){
        List<ProductModel> list = productRepository.findAll();
        if (list.isEmpty()){
            return 1L;
        } else {
            return (long) (list.size() + 1);
        }
    }


    public ResponseEntity<List<ProductModel>> findAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    public ResponseEntity<ProductModel> changeProduct(Long id,
                                                      ProductCreateDTO dto) {
        try {
            ProductModel product = productRepository.findByProductId(id).get();
            if(dto.getProductName() != null && dto.getProductName() != ""){
                product.setProductName(dto.getProductName());
            }
            if(dto.getProductDescription() != null && dto.getProductDescription() != ""){
                product.setProductDescription(dto.getProductDescription());
            }
            if(dto.getProductPrice() != null){
                product.setProductPrice(dto.getProductPrice());
            }
            if(dto.getProductClassification() != null){
                product.setProductClassification(dto.getProductClassification());
            }
            return ResponseEntity.ok(product);

        } catch (BadRequestException e){
            throw new BadRequestException("houve um erro ao tentar alterar o produto, por favor contate o suporte");
        }
    }


    public ResponseEntity<String> deleteProduct(Long productId) {
       try {
           ProductModel product = productRepository.findByProductId(productId).get();
           productRepository.delete(product);
           return ResponseEntity.ok("Produto deletado com sucesso!");
       } catch (BadRequestException e) {
           throw new BadRequestException("houve um problema ao tentar deletar o produto, por favor contate o suporte");
       }
    }

    public ResponseEntity<List<ProductModel>> searchQueryProducts(String productName,
                                                                  Double maxPrice,
                                                                  Double minPrice) {
        Specification<ProductModel> productModelSpecification;
        try {
            if(validParameteres(productName, maxPrice, minPrice)){
                productModelSpecification = Specification.where(null);
                if(productName != null && !productName.isEmpty()){
                    productModelSpecification = productModelSpecification.and(getProductSpecificationName(productName));
                }
                if (maxPrice != null){
                    productModelSpecification = productModelSpecification.and(getProductSpecificationMaxPrice(maxPrice));
                }
                if(minPrice != null){
                    productModelSpecification = productModelSpecification.and(getProductSpecificationMinPrice(minPrice));
                }



                return ResponseEntity.ok(productRepository.findAll(productModelSpecification));
            }

            return ResponseEntity.ok(productRepository.findAll());
        } catch (BadRequestException e) {
            throw new BadRequestException("houve um erro ao tentar buscar o produto, por favor tente novamente");
        }



    }

    public ResponseEntity<List<ProductModel>> findProductsByCategory(ProductClassification className){
        try{
             List<ProductModel> productsList = productRepository.findByProductClassification(className)
                    .get();
             return ResponseEntity.ok(productsList);
        } catch (BadRequestException e){
            throw new BadRequestException("Não foi possível buscar os produtos com essa classificação!");
        }


    }


    private boolean validParameteres(String name,
                                     Double maxPrice,
                                     Double minPrice){
        return !((name == null || name == "") && maxPrice == null && minPrice == null);
    }

    private ProductSpecification getProductSpecificationName(String q){
        ProductSpecification nameSpecification;
        nameSpecification=ProductSpecification.builder()
                .criteria(SearchCriteria.builder()
                        .key("productName")
                        .value(q)
                        .operation("%")
                        .build())
                .build();
        return nameSpecification;
    }

    private ProductSpecification getProductSpecificationMinPrice(Double min_price){
        ProductSpecification minPriceSpecification;
        minPriceSpecification=ProductSpecification.builder()
                .criteria(SearchCriteria.builder()
                        .key("productPrice")
                        .value(min_price)
                        .operation(">=")
                        .build())
                .build();
        return minPriceSpecification;
    }

    private ProductSpecification getProductSpecificationMaxPrice(Double max_price) {
        ProductSpecification maxPriceSpecification;
        maxPriceSpecification = ProductSpecification.builder()
                .criteria(SearchCriteria.builder()
                        .key("productPrice")
                        .value(max_price)
                        .operation("<=")
                        .build())
                .build();
        return maxPriceSpecification;
    }

}
