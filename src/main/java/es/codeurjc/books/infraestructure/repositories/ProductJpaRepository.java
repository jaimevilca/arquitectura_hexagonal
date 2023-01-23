package es.codeurjc.books.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.books.infraestructure.models.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

}
