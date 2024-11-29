package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestBody Product product) {
        return cartService.addProduct(product);
    }

    @GetMapping("/cart-items")
    public List<Product> getCartItems() {
        return cartService.getCartItems();
    }

    @DeleteMapping("/delete-item/{name}")
    public String deleteItem(@PathVariable("name") String name) {
        return cartService.removeProduct(name);
    }

    @GetMapping("/calculate-price")
    public double calculateCartTotal() {
        return cartService.calculateCartTotal();
    }

    @GetMapping("/total")
    public double totalWithTax() {
        return cartService.calculateTax();
    }
}
