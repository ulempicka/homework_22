package com.example.demo.exercise.repository;

import com.example.demo.exercise.model.Category;
import com.example.demo.exercise.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("Czekolada deserowa Wedel", 3.5, Category.GROCERY));
        products.add(new Product("Blender Braun", 100, Category.HOUSEHOLD));
        products.add(new Product("Talerz", 8, Category.OTHERS));
    }

    public void save(Product product) {
        products.add(product);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public List<Product> findPerCategory(Category category) {
        return products.stream()
                .filter(product -> product.getCategory() == category)
                .collect(Collectors.toList());
    }
}
