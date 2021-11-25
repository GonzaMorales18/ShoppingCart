package com.informatorio.shoppingcart.controller;

import javax.persistence.EntityNotFoundException;

import com.informatorio.shoppingcart.dto.CartOperation;
import com.informatorio.shoppingcart.entity.Cart;
import com.informatorio.shoppingcart.entity.CartLine;
import com.informatorio.shoppingcart.entity.Product;
import com.informatorio.shoppingcart.entity.User;
import com.informatorio.shoppingcart.repository.CartRepository;
import com.informatorio.shoppingcart.repository.ProductRepository;
import com.informatorio.shoppingcart.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    private UserRepository userRepository;
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    @Autowired
    public CartController(UserRepository userRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @PostMapping(value = "/user/{idUser}/carts")
    public ResponseEntity<?> setCart(@PathVariable ("idUser") Long idUser, @RequestBody Cart cart){
        User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        cart.setUser(user);
        return new ResponseEntity(cartRepository.save(cart), HttpStatus.CREATED);
    }

    @PutMapping(value = "carts/{idCart}")
    public ResponseEntity<?> addProduct(@PathVariable ("idCart") Long idCart, @RequestBody CartOperation cartOperation) throws Exception{
        Cart cart = cartRepository.findById(idCart).orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));
        Product product = productRepository.findById(cartOperation.getIdProduct()).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        CartLine cartLine = new CartLine();
        cartLine.setCart(cart);
        cartLine.setProduct(product);
        cartLine.setAmount(cartOperation.getAmounth());
        cart.addCartLine(cartLine);
        return new ResponseEntity(cartRepository.save(cart), HttpStatus.CREATED);
    }

    @GetMapping(value = "carts/{idCart}")
    public ResponseEntity<Cart> getCartById(@PathVariable ("idCart") Long idCart){
        Cart cart = cartRepository.findById(idCart).orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));
        return new ResponseEntity(cart, HttpStatus.OK);
    }

}
