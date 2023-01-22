package es.codeurjc.books.services.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import es.codeurjc.books.dtos.requests.UpdateProductQuantityRequestDto;
import es.codeurjc.books.dtos.responses.UserResponseDto;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import es.codeurjc.books.dtos.requests.ProductRequestDto;
import es.codeurjc.books.dtos.responses.ProductResponseDto;
import es.codeurjc.books.exceptions.ProductNotFoundException;
import es.codeurjc.books.models.Product;
import es.codeurjc.books.repositories.ProductRepository;
import es.codeurjc.books.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private Mapper mapper;
    private ProductRepository bookRepository;

    public ProductServiceImpl(Mapper mapper, ProductRepository bookRepository) {
        this.mapper = mapper;
        this.bookRepository = bookRepository;
    }

    public Collection<ProductResponseDto> findAll() {
        return this.bookRepository.findAll().stream()
                .map(book -> this.mapper.map(book, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    public ProductResponseDto save(ProductRequestDto bookRequestDto) {
        Product book = this.mapper.map(bookRequestDto, Product.class);
        book = this.bookRepository.save(book);
        return this.mapper.map(book, ProductResponseDto.class);
    }

    public ProductResponseDto findById(long bookId) {
        Product book = this.bookRepository.findById(bookId).orElseThrow(ProductNotFoundException::new);
        return this.mapper.map(book, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto update(long productId, long quantity, UpdateProductQuantityRequestDto updateProductQuantityRequestDto) {

        Product product = this.bookRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        product.setStock((int) quantity);
        product = this.bookRepository.save(product);

        return this.mapper.map(product, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto delete(long productId) {
        Product product = this.bookRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        this.bookRepository.delete(product);
        return this.mapper.map(product, ProductResponseDto.class);
    }

}
