package es.codeurjc.books.models;


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
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    private Collection<ShoppingCartDetail> details = Collections.emptyList();

}
