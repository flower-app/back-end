package bohun.flower.app.controller;

import bohun.flower.app.dto.product.ProductDtoWithOutParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import bohun.flower.app.dto.product.ProductDto;
import bohun.flower.app.dto.product.CreateProductRequestDto;
import bohun.flower.app.service.product.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Product management", description = "Endpoints for managing products")
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @Operation(description = "Get all products")
    @ApiResponse(responseCode = "200", description = "All products")
    public List<ProductDto> getAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping("/search")
    public List<ProductDto> searchBooks(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }

    @GetMapping("/{id}/product-color")
    @Operation(summary = "Get products by color",
            description = "Get products from DB by color")
    public List<ProductDtoWithOutParams> getProductsByColor(@PathVariable Long id) {
        return productService.findProductsByColorId(id);
    }

    @GetMapping("/{id}/product-type")
    @Operation(summary = "Get products by type",
            description = "Get products from DB by type")
    public List<ProductDtoWithOutParams> getProductsByType(@PathVariable Long id) {
        return productService.findProductsByTypeId(id);
    }

    @GetMapping("/{id}/product-season")
    @Operation(summary = "Get products by season",
            description = "Get products from DB by season")
    public List<ProductDtoWithOutParams> getProductsBySeason(@PathVariable Long id) {
        return productService.findProductsBySeasonId(id);
    }

    @GetMapping("/{id}/product-contain")
    @Operation(summary = "Get products by contains",
            description = "Get products from DB by contains")
    public List<ProductDtoWithOutParams> getProductsByContains(@PathVariable Long id) {
        return productService.findProductsByContainsId(id);
    }

    @GetMapping("/{id}/product-discount")
    @Operation(summary = "Get products by discount",
            description = "Get products from DB by discount")
    public List<ProductDtoWithOutParams> getProductsByDiscount(@PathVariable Long id) {
        return productService.findProductsByDiscountId(id);
    }

    @GetMapping("/{id}/product-size")
    @Operation(summary = "Get products by size",
            description = "Get products from DB by size")
    public List<ProductDtoWithOutParams> getProductsBySize(@PathVariable Long id) {
        return productService.findProductsBySizeId(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by Id",
            description = "Get product from DB by ID")
    public ProductDto getBookById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @GetMapping("/price")
    @Operation(description = "Get all products by price")
    @ApiResponse(responseCode = "200", description = "All products by price")
    public List<ProductDto> getAll(
            @RequestParam(required = false) BigDecimal fromPrice,
            @RequestParam(required = false) BigDecimal toPrice,
            Pageable pageable
    ) {
        return productService.findAllByPriceInService(fromPrice, toPrice, pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(description = "Create new product")
    @ApiResponse(responseCode = "201", description = "Create new product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody @Valid CreateProductRequestDto productRequestDto) {
        return productService.save(productRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{productId}")
    @Operation(summary = "Update product", description = "Update product")
    @ApiResponse(responseCode = "200", description = "Update product",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))})
    public ProductDto updateBook(@PathVariable Long productId,
                                 @RequestBody CreateProductRequestDto bookRequestDto) {
        return productService.updateProduct(productId, bookRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete product by Id", description = "Delete product by Id")
    @ApiResponse(responseCode = "200", description = "Delete product")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
