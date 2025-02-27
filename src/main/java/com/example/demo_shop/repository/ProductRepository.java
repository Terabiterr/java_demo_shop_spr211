package com.example.demo_shop.repository;

import com.example.demo_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name); //SELECT * from Products WHERE name = ?;

    List<Product> findAll(); //SELECT * FROM Products;

    Optional<Product> findById(long id);

    Product save(Product product);

    void deleteById(long id);
}
