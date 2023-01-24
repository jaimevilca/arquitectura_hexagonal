package es.codeurjc.books.domain.port;


import java.util.Collection;
import java.util.Optional;

public interface CartUseCase {

    Collection<ShoppingCartDto> findAll();

    ShoppingCartDto save(ShoppingCartDto shoppingCartDto);

    Optional<ShoppingCartDto> findById(long cartId);

    void delete(ShoppingCartDto shoppingCartDto);


}