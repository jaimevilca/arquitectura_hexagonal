package es.codeurjc.books.infraestructure.models;


import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "shopping_cart_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "comments")
public class ShoppingCartDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private int quantity;

    @ManyToOne
    private ShoppingCartEntity cart;

}
