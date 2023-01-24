package es.codeurjc.books.domain.port;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository {
    Collection<ProductDto> findAll();

    FullProductDto save(FullProductDto productDto);

    Optional<FullProductDto> findById(long productId);

    void delete(FullProductDto product);
}
