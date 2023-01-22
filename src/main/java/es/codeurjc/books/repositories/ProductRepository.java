package es.codeurjc.books.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.books.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
