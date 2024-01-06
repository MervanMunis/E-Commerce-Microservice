package com.microservices.ProductService.controller;

import com.microservices.ProductService.dto.ProductRequestDto;
import com.microservices.ProductService.dto.ProductResponseDto;
import com.microservices.ProductService.external.response.CompanyWithProductsResponseDto;
import com.microservices.ProductService.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private final IProductService productService;

    // OLMAYAN BİR COMPANYID İÇİN PRODUCT ÜRETEBİLİYOR BUNU ÇÖZ
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@ModelAttribute ProductRequestDto productRequestDto) throws IOException {
        ProductResponseDto responseDto = productService.createProduct(productRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("productId") Long productId) {
        ProductResponseDto responseDto = productService.getProductById(productId);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> responseDtoList = productService.getAllProducts();
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{companyId}/products-with-company")
    public ResponseEntity<CompanyWithProductsResponseDto> getAllProductsWithCompany(@PathVariable("companyId") Long companyId) {

        CompanyWithProductsResponseDto companyWithProductsResponseDto = productService.getAllProductsWithCompanyId(companyId);
        // Return the response
        return ResponseEntity.ok(companyWithProductsResponseDto);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("productId") Long productId,
                                                            @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto responseDto = productService.updateProduct(productId, productRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long productId, @RequestParam long quantity){
        productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
        String responseMessage = productService.deleteProduct(productId);
        return ResponseEntity.ok(responseMessage);
    }
}
