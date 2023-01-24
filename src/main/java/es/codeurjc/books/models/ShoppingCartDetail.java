package es.codeurjc.books.models;


import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "shopping_cart_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "comments")
public class ShoppingCartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long prodictPrice;

    private int quantity;

    @ManyToOne
    private ShoppingCart cart;

}
