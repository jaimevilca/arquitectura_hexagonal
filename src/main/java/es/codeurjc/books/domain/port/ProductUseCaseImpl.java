package es.codeurjc.books.domain.port;

import java.util.Collection;
import java.util.Optional;

public class ProductUseCaseImpl implements  ProductUseCase{

    private ProductRepositoty productRepositoty;

    public ProductUseCaseImpl(ProductRepositoty productRepositoty){
        this.productRepositoty = productRepositoty;
    }

    @Override
    public Collection<ProductDto> findAll() {
        return productRepositoty.findAll();
    }

    @Override
    public FullProductDto save(FullProductDto productDto) {
        return productRepositoty.save(productDto);
    }

    @Override
    public Optional<FullProductDto> findById(long productId) {
        return productRepositoty.findById(productId);
    }

    @Override
    public void delete(FullProductDto product) {
        productRepositoty.delete(product);
    }
}
