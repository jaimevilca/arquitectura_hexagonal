package es.codeurjc.books.services;

import java.util.Collection;

import es.codeurjc.books.dtos.requests.ProductRequestDto;
import es.codeurjc.books.dtos.requests.UpdateProductQuantityRequestDto;
import es.codeurjc.books.dtos.responses.ProductResponseDto;

public interface ProductService {

    Collection<ProductResponseDto> findAll();

    ProductResponseDto save(ProductRequestDto bookRequestDto);

    ProductResponseDto findById(long bookId);

    ProductResponseDto update(long productId, long quantity, UpdateProductQuantityRequestDto updateProductQuantityRequestDto);

    ProductResponseDto delete(long productId);
}
