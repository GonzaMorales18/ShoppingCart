package com.informatorio.shoppingcart.controller;

import com.informatorio.shoppingcart.entity.Product;
import com.informatorio.shoppingcart.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/products")
    public ResponseEntity<Product> getAll(){
        return new ResponseEntity(productRepository.findAll(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/products")
    public ResponseEntity<?> set(@RequestBody Product product){
        return new ResponseEntity(productRepository.save(product), HttpStatus.CREATED);
    }

}