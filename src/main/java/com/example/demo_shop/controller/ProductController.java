package com.example.demo_shop.controller;
import com.example.demo_shop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    } //Thymeleaf
}
