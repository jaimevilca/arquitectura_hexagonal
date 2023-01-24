package es.codeurjc.books.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartRequestDto {
    @NotBlank(message = "User nick is mandatory")
    private String userNick;

}
