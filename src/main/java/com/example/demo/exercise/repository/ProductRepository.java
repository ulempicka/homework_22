package com.example.demo.exercise.repository;

import com.example.demo.exercise.model.Category;
import com.example.demo.exercise.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("Czekolada deserowa Wedel", 3.5, Category.SPOZYWCZE));
        products.add(new Product("Blender Braun", 100, Category.GOSP_DOM));
        products.add(new Product("Talerz", 8, Category.INNE));
    }

    public void save(Product product) {
        products.add(product);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public List<Product> findPerCategory(Category category) {
        List<Product> productsPerCategory = new ArrayList<>();
        for (Product product : products) {
            if(product.getCategory().equals(category)){
                productsPerCategory.add(product);
            }
        }
        return productsPerCategory;
    }
}
