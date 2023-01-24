package es.codeurjc.books.infraestructure;

import es.codeurjc.books.domain.port.ShoppingCartDto;
import es.codeurjc.books.domain.port.ShoppingCartRepository;
import es.codeurjc.books.infraestructure.models.ShoppingCartEntity;
import es.codeurjc.books.infraestructure.repositories.ShoppingCartJpaRepository;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartRepositoryAdapter implements ShoppingCartRepository {

    private Mapper mapper;
    private ShoppingCartJpaRepository shoppingCartRepository;


    public ShoppingCartRepositoryAdapter(Mapper mapper, ShoppingCartJpaRepository shoppingCartRepository) {
        this.mapper = mapper;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public Collection<ShoppingCartDto> findAll() {
        return this.shoppingCartRepository
                .findAll()
                .stream()
                .map(product -> mapper.map(product, ShoppingCartDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShoppingCartDto save(ShoppingCartDto cartDto) {
        ShoppingCartEntity cart = this.mapper.map(cartDto, ShoppingCartEntity.class);
        this.shoppingCartRepository.save(cart);
        return this.mapper.map(cart, ShoppingCartDto.class);
    }

    @Override
    public Optional<ShoppingCartDto> findById(long cartId) {
        return this.shoppingCartRepository
                .findById(cartId)
                .map(product -> mapper.map(product, ShoppingCartDto.class));
    }

    @Override
    public void delete(ShoppingCartDto dto) {
        ShoppingCartEntity entity = this.mapper.map(dto, ShoppingCartEntity.class);
        this.shoppingCartRepository.delete(entity);
    }


}
