package com.project.ecommerce.controller;

import com.project.ecommerce.dto.ProductCreateDTO;
import com.project.ecommerce.model.ProductModel;
import com.project.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/products")
public class ProductController implements ProductControllerOpenApi{
    @Autowired
    private ProductService productService;

    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "produto registrado com sucesso!"))
    @PostMapping(path = "/register")
    public ResponseEntity<ProductModel> registerProduct(@Valid @RequestBody ProductCreateDTO dto){
        return productService.registerProduct(dto);

    }

    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "todos os produtos:"))
    @GetMapping(path = "/findAllProducts")
    public ResponseEntity<List<ProductModel>> findAllProducts(){
        return productService.findAllProducts();
    }

    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "produto alterado com sucesso!"))
    @PutMapping(path = "/changeProduct/{productId}")
    public ResponseEntity<ProductModel> changeProduct(@Valid @PathVariable Long productId,
                                                      @Valid @RequestBody(required = false)ProductCreateDTO dto){
        return productService.changeProduct(productId,dto);
    }

    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "produto deletado com sucesso!"))
    @DeleteMapping(path = "/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@Valid @PathVariable Long productId){
        return productService.deleteProduct(productId);
    }

    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "produtos que atendem à descrição:"))
    @GetMapping(path = "/search")
    public ResponseEntity<List<ProductModel>> searchQueryProducts(
            @RequestParam(required = false)String productName,
            @RequestParam(required = false)Double maxPrice,
            @RequestParam(required = false)Double minPrice){
       return productService.searchQueryProducts(productName, maxPrice, minPrice);
    }

}
