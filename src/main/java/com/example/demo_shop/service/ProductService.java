package com.example.demo_shop.service;

import com.example.demo_shop.model.Product;
import com.example.demo_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }
    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContaining(name);
    }
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(long id, Product product) {
        Optional<Product> productFound = productRepository.findById(id);
        if (productFound.isPresent()) {
            return productRepository.save(product);
        }
        return productFound.get();
    }
    public Boolean deleteProduct(long id) {
        Optional<Product> productFound = productRepository.findById(id);
        if (productFound.isPresent()) {
            productRepository.delete(productFound.get());
            return true;
        }
        return false;
    }
}
