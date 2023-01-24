package es.codeurjc.books.domain.port;

import java.util.Collection;
import java.util.Optional;

public class ProductUseCaseImpl implements ProductUseCase {

    private ProductRepository productRepository;

    public ProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Collection<ProductDto> findAll() {
        return productRepository.findAll();
    }

    @Override
    public FullProductDto save(FullProductDto productDto) {
        return productRepository.save(productDto);
    }

    @Override
    public Optional<FullProductDto> findById(long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public void delete(FullProductDto product) {
        productRepository.delete(product);
    }
}
