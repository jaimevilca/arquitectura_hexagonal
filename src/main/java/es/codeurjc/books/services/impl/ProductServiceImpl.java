package es.codeurjc.books.services.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import es.codeurjc.books.domain.port.FullProductDto;
import es.codeurjc.books.domain.port.ProductUseCase;
import es.codeurjc.books.dtos.requests.UpdateProductQuantityRequestDto;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import es.codeurjc.books.dtos.requests.ProductRequestDto;
import es.codeurjc.books.dtos.responses.ProductResponseDto;
import es.codeurjc.books.exceptions.ProductNotFoundException;
import es.codeurjc.books.infraestructure.repositories.ProductJpaRepository;
import es.codeurjc.books.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private Mapper mapper;
    private ProductUseCase productUseCase;

    public ProductServiceImpl(Mapper mapper, ProductUseCase productUseCase) {
        this.mapper = mapper;
        this.productUseCase = productUseCase;
    }

    public Collection<ProductResponseDto> findAll() {
        return this.productUseCase
                .findAll()
                .stream()
                .map( product -> mapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    public ProductResponseDto save(ProductRequestDto bookRequestDto) {
        FullProductDto productDto = mapper.map(bookRequestDto, FullProductDto.class);
        FullProductDto savedProductDto = this.productUseCase.save(productDto);
        return this.mapper.map(savedProductDto, ProductResponseDto.class);
    }

    public ProductResponseDto findById(long productId) {
        FullProductDto productDto = this.productUseCase.findById(productId).orElseThrow(ProductNotFoundException::new);
        return this.mapper.map(productDto, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto update(long productId, long quantity, UpdateProductQuantityRequestDto updateProductQuantityRequestDto) {
        FullProductDto product = this.productUseCase.findById(productId).orElseThrow(ProductNotFoundException::new);
        product.setStock((int) quantity);
        product = this.productUseCase.save(product);
        return this.mapper.map(product, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto delete(long productId) {
        FullProductDto product = this.productUseCase.findById(productId).orElseThrow(ProductNotFoundException::new);
        this.productUseCase.delete(product);
        return this.mapper.map(product, ProductResponseDto.class);
    }

}
