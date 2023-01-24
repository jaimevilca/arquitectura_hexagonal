package es.codeurjc.books.services.impl;

import es.codeurjc.books.domain.port.*;
import es.codeurjc.books.dtos.requests.ShoppingCartRequestDto;
import es.codeurjc.books.dtos.responses.ShoppingCartResponseDto;
import es.codeurjc.books.services.ShoppingCartService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
public class ShoppingCartImp implements ShoppingCartService {

    private Mapper mapper;
    private ProductUseCase productRepository;

    private CartUseCase shoppingCartRepository;

    public ShoppingCartImp(Mapper mapper, ProductUseCase productRepository, CartUseCase shoppingCartRepository) {
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public ShoppingCartResponseDto save(ShoppingCartRequestDto shoppingCartRequestDto) {
        ShoppingCartDto cart = this.mapper.map(shoppingCartRequestDto, ShoppingCartDto.class);
        cart.setStatus("PENDING");
        this.shoppingCartRepository.save(cart);
        return this.mapper.map(cart, ShoppingCartResponseDto.class);
    }

    @Override
    public ShoppingCartResponseDto completeShoppingCart(long cartId) {
        ShoppingCartDto cart = this.shoppingCartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Shopping cart not found"));
        cart.setStatus("COMPLETED");
        this.shoppingCartRepository.save(cart);
        return this.mapper.map(cart, ShoppingCartResponseDto.class);
    }

    @Override
    public ShoppingCartResponseDto findById(long cartId) {
        ShoppingCartDto cart = this.shoppingCartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Shopping cart not found"));
        return this.mapper.map(cart, ShoppingCartResponseDto.class);
    }

    @Override
    public ShoppingCartResponseDto delete(long cartId) {
        ShoppingCartDto cart = this.shoppingCartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Shopping cart not found"));
        this.shoppingCartRepository.delete(cart);
        return this.mapper.map(cart, ShoppingCartResponseDto.class);
    }

    @Override
    public ShoppingCartResponseDto updateProduct(long cartId, long productId, int quantity) {
        ShoppingCartDto cart = this.shoppingCartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Shopping cart not found"));
        Optional<ShoppingCartDetailDto> detail = cart.getDetails().stream().filter(i -> productId == i.getProductId()).findFirst();
        if (!validStock(quantity, productId)) {
            throw new UnsupportedOperationException("Insufficient stock");
        }
        if (detail.isPresent()) {
            detail.get().setQuantity(quantity);
        }
        this.shoppingCartRepository.save(cart);

        return this.mapper.map(cart, ShoppingCartResponseDto.class);
    }

    @Override
    public ShoppingCartResponseDto removeProduct(long cartId, long productId) {
        ShoppingCartDto cart = this.shoppingCartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Shopping cart not found"));
        Optional<ShoppingCartDetailDto> detail = cart.getDetails().stream().filter(i -> productId == i.getProductId()).findFirst();
        if (detail.isPresent()) {
            cart.getDetails().remove(detail.get());
            this.shoppingCartRepository.save(cart);
        }
        return this.mapper.map(cart, ShoppingCartResponseDto.class);
    }

    private boolean validStock(int quantity, long productId) {
        FullProductDto product = this.productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        return quantity <= product.getStock();
    }
}
