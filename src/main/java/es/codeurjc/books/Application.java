package es.codeurjc.books;

import es.codeurjc.books.domain.port.*;
import es.codeurjc.books.infraestructure.ProductRepositoryAdapter;
import es.codeurjc.books.infraestructure.ShoppingCartRepositoryAdapter;
import es.codeurjc.books.infraestructure.repositories.ProductJpaRepository;
import es.codeurjc.books.infraestructure.repositories.ShoppingCartJpaRepository;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper(Arrays.asList("dozer_mapping.xml"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ProductUseCase productUseCase(ProductRepository productRepository) {
        return new ProductUseCaseImpl(productRepository);
    }

    @Bean
    public ProductRepository productRepository(ProductJpaRepository productJpaRepository, Mapper mapper) {
        return new ProductRepositoryAdapter(productJpaRepository, mapper);
    }

    @Bean
    public CartUseCase cartUseCase(ShoppingCartRepository shoppingCartRepository) {
        return new CartUseCaseImpl(shoppingCartRepository);
    }

    @Bean
    public ShoppingCartRepository shoppingCartRepository(ShoppingCartJpaRepository shoppingCartJpaRepository, Mapper mapper) {
        return new ShoppingCartRepositoryAdapter(mapper, shoppingCartJpaRepository);
    }

}
