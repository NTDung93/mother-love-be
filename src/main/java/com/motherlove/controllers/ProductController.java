package com.motherlove.controllers;

import com.motherlove.models.payload.dto.ProductDto;
import com.motherlove.services.IProductService;
import com.motherlove.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @Operation(summary = "Get List Products", description = "Get List Products")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    @GetMapping
    public ResponseEntity<Object> getAllProducts(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "productId", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return ResponseEntity.ok(productService.getAllProducts(pageNo, pageSize, sortBy, sortDir));
    }

    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    @PreAuthorize("hasRole('ROLE_STAFF') or hasRole('ROLE_ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<Object> searchProducts(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "productId", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(value = "status", required = false) List<Integer> status,
            @RequestParam(value = "productName", required = false) Boolean productName,
            @RequestParam(value = "brandName", required = false) List<String> brandName,
            @RequestParam(value = "categoryName", required = false) List<String> categoryName
    ) {
        Map<String, Object> searchParams = new HashMap<>();

        if (status != null && !status.isEmpty()) searchParams.put("status", status);
        if (productName != null) searchParams.put("productName", productName);
        if (brandName != null) searchParams.put("brandName", brandName);
        if (categoryName != null) searchParams.put("categoryName", categoryName);

        return ResponseEntity.ok(productService.searchProduct(pageNo, pageSize, sortBy, sortDir, searchParams));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getCategory(@PathVariable long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ROLE_STAFF') or hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDto> addCategory(@RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.addProduct(productDto);
        return ResponseEntity.ok(savedProduct);
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ROLE_STAFF') or hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateCategory(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ROLE_STAFF') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDto> deleteCategory(@PathVariable(name = "id") long id) {
        ProductDto deletedProductDto = productService.deleteProduct(id);
        return ResponseEntity.ok(deletedProductDto);
    }
}
