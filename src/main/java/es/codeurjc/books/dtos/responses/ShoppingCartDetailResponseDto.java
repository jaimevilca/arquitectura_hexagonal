package es.codeurjc.books.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartDetailResponseDto {

    private Long id;

    private Long productId;

    private String name;

    private Long price;

    private int quantity;

}
