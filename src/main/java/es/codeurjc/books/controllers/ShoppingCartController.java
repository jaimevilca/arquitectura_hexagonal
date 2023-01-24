package es.codeurjc.books.controllers;

import es.codeurjc.books.dtos.requests.ShoppingCartRequestDto;
import es.codeurjc.books.dtos.responses.ShoppingCartResponseDto;
import es.codeurjc.books.services.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/shoppingcarts")
public class ShoppingCartController {

    private ShoppingCartService service;


    public ShoppingCartController(ShoppingCartService service) {
        this.service = service;
    }


    @PostMapping("/")
    public ShoppingCartResponseDto save(@Valid @RequestBody ShoppingCartRequestDto shoppingCartRequestDto) {
        return this.service.save(shoppingCartRequestDto);
    }

    @PatchMapping("/{cartId}")
    public ShoppingCartResponseDto completeCart(@PathVariable long cartId) {
        return this.service.completeShoppingCart(cartId);
    }

    @GetMapping("/{cartId}")
    public ShoppingCartResponseDto getShoppingCart(@PathVariable long cartId) {
        return this.service.findById(cartId);
    }

    @DeleteMapping("/{cartId}")
    public ShoppingCartResponseDto deleteShoppingCart(@PathVariable long cartId) {
        return this.service.delete(cartId);
    }

    @PutMapping("/{cartId}/{productId}/{quantity}")
    public ShoppingCartResponseDto updateProduct(@PathVariable long cartId, @PathVariable long productId, @PathVariable int quantity) {
        return this.service.updateProduct(cartId, productId, quantity);
    }


}
