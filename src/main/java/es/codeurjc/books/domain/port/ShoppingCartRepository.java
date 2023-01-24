package es.codeurjc.books.domain.port;

import java.util.Collection;
import java.util.Optional;

public interface ShoppingCartRepository {
    Collection<ShoppingCartDto> findAll();

    ShoppingCartDto save(ShoppingCartDto dto);

    Optional<ShoppingCartDto> findById(long cartId);

    void delete(ShoppingCartDto cart);
}
