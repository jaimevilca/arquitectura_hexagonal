package es.codeurjc.books.infraestructure.models;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


@Entity
@Table(name = "shopping_carts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "comments")
public class ShoppingCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userNick;
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    private Collection<ShoppingCartDetailEntity> details = Collections.emptyList();

}
