package com.example.demo_shop.service;

import com.example.demo_shop.model.Product;
import com.example.demo_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }
    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContaining(name);
    }
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
