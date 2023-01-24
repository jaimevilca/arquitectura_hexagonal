package es.codeurjc.books.domain.port;

import java.util.Collection;
import java.util.Optional;

public class CartUseCaseImpl implements CartUseCase {

    private ShoppingCartRepository shoppingCartRepository;


    public CartUseCaseImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public Collection<ShoppingCartDto> findAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCartDto save(ShoppingCartDto shoppingCartDto) {
        return shoppingCartRepository.save(shoppingCartDto);
    }

    @Override
    public Optional<ShoppingCartDto> findById(long cartId) {
        return shoppingCartRepository.findById(cartId);
    }

    @Override
    public void delete(ShoppingCartDto shoppingCartDto) {
        shoppingCartRepository.delete(shoppingCartDto);
    }


}
