package com.microservices.ProductService.service.impl;

import com.microservices.ProductService.dto.ProductRequestDto;
import com.microservices.ProductService.dto.ProductResponseDto;
import com.microservices.ProductService.entity.Product;
import com.microservices.ProductService.exception.ResourceNotFoundException;
import com.microservices.ProductService.external.client.ICompanyFeignService;
import com.microservices.ProductService.external.response.CompanyWithProductsResponseDto;
import com.microservices.ProductService.mapper.ProductMapper;
import com.microservices.ProductService.repository.IProductRepository;
import com.microservices.ProductService.service.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Log4j2
@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    private final ICompanyFeignService companyFeignService;

    // Create a new product
    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws IOException {
        log.info("Creating The Product...");

        Product product = ProductMapper.mapToProduct(productRequestDto);

        Product savedProduct = productRepository.save(product);

        log.info("Product Created.");
        return ProductMapper.mapToProductResponseDto(savedProduct);
    }

    // Get product details by productId
    @Override
    public ProductResponseDto getProductById(Long productId) {
        log.info("Get Product For The productId: {}", productId);

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Company Does Not Exist With The Given Id: "  + productId,
                        "COMPANY_NOT_FOUND",
                        404)
        );

        return ProductMapper.mapToProductResponseDto(product);
    }

    // Get all products
    @Override
    public List<ProductResponseDto> getAllProducts() {
        log.info("Finding All Products.");

        List<Product> products = productRepository.findAll();

        log.info("All Products Are Listed.");
        return products.stream().map(ProductMapper::mapToProductResponseDto)
                .collect(Collectors.toList());
    }

    // Get company details along with associated products
    @Override
    public CompanyWithProductsResponseDto getAllProductsWithCompanyId(Long companyId){

        // Fetch products associated with the companyId
        List<Product> products = productRepository.findByCompanyId(companyId);

        // Now, call the Feign client to get company details
        CompanyWithProductsResponseDto companyWithProductsResponseDto = companyFeignService.getCompanyById(companyId);

        // Map products to ProductResponseDto
        List<CompanyWithProductsResponseDto.ProductResponseDto> productResponseDtos = products
                .stream().map(ProductMapper::mapToProductCompanyResponseDto).collect(Collectors.toList());

        // Merge the product and company details as needed
        log.info("Merging The Product and Company Details...");
        CompanyWithProductsResponseDto responseDto = ProductMapper
                .mapToCompanyWithProductsResponseDto(
                        companyId,
                        companyWithProductsResponseDto,
                        productResponseDtos);

        log.info("Products with company details: {}", responseDto.getCompanyId());
        return responseDto;
    }

    // Update product details
    @Override
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto) {
        log.info("Finding The Product To Update...");

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Product Does Not Exist With The Given Id: "  + productId,
                        "PRODUCT_NOT_FOUND",
                        404)
        );

        product.setProductName(productRequestDto.getProductName());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setDescription((productRequestDto.getDescription()));
        product.setCategory(productRequestDto.getCategory());
        productRepository.save(product);

        log.info("Product is updated.");
        return ProductMapper.mapToProductResponseDto(product);
    }

    // Reduce the quantity of a product
    @Override
    public void reduceQuantity(Long productId, Long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity, productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + productId,
                        "PRODUCT_NOT_FOUND",
                        404));

        // Check if sufficient quantity is available
        if (product.getQuantity() < quantity){
            throw new ResourceNotFoundException(
                    "Product not found with id: " + productId,
                    "PRODUCT_NOT_FOUND",
                    404);
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        log.info("Product Quantity Is Reduced and Updated Successfully");
    }

    // Delete a product
    @Override
    public String deleteProduct(Long productId) {
        log.info("Deleting TheCompany...");
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Company Does Not Exist With The Given Id: "  + productId,
                        "PRODUCT_NOT_FOUND",
                        404)
        );
        productRepository.delete(product);
        return "Product is Deleted";
    }
}
