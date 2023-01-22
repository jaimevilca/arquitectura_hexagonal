package es.codeurjc.books.controllers;

import java.util.Collection;

import javax.validation.Valid;

import es.codeurjc.books.dtos.requests.ProductRequestDto;
import es.codeurjc.books.dtos.requests.UpdateProductQuantityRequestDto;
import es.codeurjc.books.dtos.responses.ProductResponseDto;
import es.codeurjc.books.dtos.responses.UserResponseDto;
import es.codeurjc.books.services.ProductService;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all products",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class)))})})
    @GetMapping("/")
    public Collection<ProductResponseDto> getProducts() {
        return this.productService.findAll();
    }

    @Operation(summary = "Get a product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "product not found",
                    content = @Content)})
    @GetMapping("/{productId}")
    public ProductResponseDto getproduct(@Parameter(description = "id of product to be searched")
                                          @PathVariable long productId) {
        return this.productService.findById(productId);
    }

    @Operation(summary = "Create a new product")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product to be created", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductRequestDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the product",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid product attributes supplied",
                    content = @Content)})
    @PostMapping("/")
    public ProductResponseDto save(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return this.productService.save(productRequestDto);
    }
    @Operation(summary = "Updates product's quantity")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product to be updated", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UpdateProductQuantityRequestDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product with updated quantity",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @PutMapping("/{productId}/stock/{quantity}")
    public ProductResponseDto update(@Parameter(description = "id of product to update the quantity")
                                            @PathVariable long productId,
                                            @PathVariable long quantity,
                                            @Valid @RequestBody UpdateProductQuantityRequestDto updateProductQuantityRequestDto) {
        return this.productService.update(productId, quantity, updateProductQuantityRequestDto);
    }

    @Operation(summary = "Deletes product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Product can't be deleted ",
                    content = @Content)})
    @DeleteMapping("/{productId}")
    public ProductResponseDto deleteUser(@Parameter(description = "id of product to be deleted") @PathVariable long productId) {
        return this.productService.delete(productId);
    }


}
