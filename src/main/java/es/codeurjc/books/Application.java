package es.codeurjc.books;

import java.util.Arrays;

import es.codeurjc.books.domain.port.ProductRepositoty;
import es.codeurjc.books.domain.port.ProductUseCase;
import es.codeurjc.books.domain.port.ProductUseCaseImpl;
import es.codeurjc.books.infraestructure.ProductRepositoryAdapter;
import es.codeurjc.books.infraestructure.repositories.ProductJpaRepository;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    public ProductUseCase productUseCase(ProductRepositoty productRepositoty){
        return new ProductUseCaseImpl(productRepositoty);
    }

    @Bean
    public ProductRepositoty productRepositoty(ProductJpaRepository productJpaRepository, Mapper mapper){
        return new ProductRepositoryAdapter(productJpaRepository, mapper);
    }

}
