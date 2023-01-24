package es.codeurjc.books.infraestructure.repositories;

import es.codeurjc.books.infraestructure.models.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartJpaRepository extends JpaRepository<ShoppingCartEntity, Long> {

}
