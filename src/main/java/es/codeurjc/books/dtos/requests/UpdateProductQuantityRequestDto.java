package es.codeurjc.books.dtos.requests;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateProductQuantityRequestDto {

    private int id;
    private int stock;

}
