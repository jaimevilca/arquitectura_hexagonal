package es.codeurjc.books.services;

import es.codeurjc.books.dtos.requests.ShoppingCartRequestDto;
import es.codeurjc.books.dtos.responses.ShoppingCartResponseDto;

public interface ShoppingCartService {

    ShoppingCartResponseDto save(ShoppingCartRequestDto shoppingCartRequestDto);

    ShoppingCartResponseDto completeShoppingCart(long cartId);

    ShoppingCartResponseDto findById(long cartId);

    ShoppingCartResponseDto delete(long cartId);

    ShoppingCartResponseDto updateProduct(long cartId, long productId, int quantity);

    ShoppingCartResponseDto removeProduct(long cartId, long productId);

}
