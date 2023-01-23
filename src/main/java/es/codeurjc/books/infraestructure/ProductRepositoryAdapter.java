package es.codeurjc.books.infraestructure;

import es.codeurjc.books.domain.port.FullProductDto;
import es.codeurjc.books.domain.port.ProductDto;
import es.codeurjc.books.domain.port.ProductRepositoty;
import es.codeurjc.books.infraestructure.models.ProductEntity;
import es.codeurjc.books.infraestructure.repositories.ProductJpaRepository;
import org.dozer.Mapper;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRepositoryAdapter implements ProductRepositoty {

    private final Mapper mapper;
    private final ProductJpaRepository productJpaRepository;

    public ProductRepositoryAdapter(ProductJpaRepository productJpaRepository, Mapper mapper) {
        this.productJpaRepository = productJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Collection<ProductDto> findAll() {
        return this.productJpaRepository
                .findAll()
                .stream()
                .map( product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FullProductDto save(FullProductDto productDto) {
        ProductEntity productEntity = this.mapper.map(productDto, ProductEntity.class);
        ProductEntity saveEntity = this.productJpaRepository.save(productEntity);
        return this.mapper.map(saveEntity, FullProductDto.class);
    }

    @Override
    public Optional<FullProductDto> findById(long productId) {
        return  this.productJpaRepository
                .findById(productId)
                .map(product -> mapper.map(product, FullProductDto.class));
    }

    @Override
    public void delete(FullProductDto product) {
        ProductEntity productEntity = this.mapper.map(product, ProductEntity.class);
        this.productJpaRepository.delete(productEntity);
    }
}
