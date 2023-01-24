package es.codeurjc.books.dtos.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;


@Data
@Getter
@Setter
public class ShoppingCartResponseDto {

    private Long id;

    private String userNick;

    private String status;

    private List<ShoppingCartDetailResponseDto> details = Collections.emptyList();

}
