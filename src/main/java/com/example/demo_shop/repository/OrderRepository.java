package com.example.demo_shop.repository;

import com.example.demo_shop.model.OrderDemo;
import com.example.demo_shop.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {
    List<OrderDemo> findByUser(User user);
}
