package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartService {

    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String addProduct(Product product) {
        product.setName(product.getName().toLowerCase()); //Saves the product in lowercase
        productRepository.save(product);
        return "Product added: " + product.getName();
    }

    public List <Product> getCartItems(){
        return productRepository.findAll();
    }
    public String removeProduct(String name) {
        if (productRepository.existsByName(name)) {
            productRepository.deleteByName(name);
            return "Product removed: " + name;
        }
        return "Product " + name + " not found.";
    }
    public double calculateCartTotal() {
        double total = 0;
        for (Product oneproduct : getCartItems()) {
            total += oneproduct.finalPrice();
        }
        return total;
    }

    public double calculateTax() {
        double tax = 1.22;
        double taxAmount = calculateCartTotal() * tax;
        return taxAmount;
    }

    public double applyDiscount (boolean hasMembership) {
        double discount = 0.10;
        if (hasMembership) {
            double discountAmount = calculateCartTotal() * discount;
            return discountAmount;
        }
        return calculateCartTotal();
    }
}
