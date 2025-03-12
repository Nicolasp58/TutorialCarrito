package com.example.springboot.controllers;

import com.example.springboot.models.Product;
import com.example.springboot.repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String index(HttpSession session, Model model) {
        // Obtener productos del repositorio
        List<Product> products = productRepository.findAll();
        
        // Obtener productos del carrito almacenados en la sesión
        Map<Long, Integer> cartProductData = (Map<Long, Integer>) session.getAttribute("cart_product_data");
        Map<Long, Product> cartProducts = new HashMap<>();

        if (cartProductData != null) {
            for (Long id : cartProductData.keySet()) {
                Optional<Product> product = productRepository.findById(id);
                product.ifPresent(p -> cartProducts.put(id, p));
            }
        }

        model.addAttribute("title", "Cart - Online Store");
        model.addAttribute("subtitle", "Shopping Cart");
        model.addAttribute("products", products);
        model.addAttribute("cartProducts", cartProducts);
        return "cart/index";
    }

    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id, HttpSession session) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return "redirect:/cart";
        }

        // Recuperar el carrito de la sesión o crear uno nuevo si es nulo
        Map<Long, Integer> cartProductData = (Map<Long, Integer>) session.getAttribute("cart_product_data");
        if (cartProductData == null) {
            cartProductData = new HashMap<>();
        }

        // Agregar producto al carrito (contador de cantidad)
        cartProductData.put(id, cartProductData.getOrDefault(id, 0) + 1);
        session.setAttribute("cart_product_data", cartProductData);
        return "redirect:/cart";
    }

    @GetMapping("/removeAll")
    public String removeAll(HttpSession session) {
        session.removeAttribute("cart_product_data");
        return "redirect:/cart";
    }
}
