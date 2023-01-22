package es.codeurjc.books.services;

import java.util.Collection;

import es.codeurjc.books.dtos.requests.UpdateProductQuantityRequestDto;
import es.codeurjc.books.dtos.requests.UserRequestDto;
import es.codeurjc.books.dtos.responses.UserResponseDto;

public interface UserService {

    Collection<UserResponseDto> findAll();

    UserResponseDto save(UserRequestDto userRequestDto);

    UserResponseDto findById(long userId);

    UserResponseDto updateEmail(long userId, UpdateProductQuantityRequestDto updateUserEmailRequestDto);

    UserResponseDto delete(long userId);

}
