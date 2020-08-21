package com.example.demo.exercise.controller;

import com.example.demo.exercise.model.Category;
import com.example.demo.exercise.model.Product;
import com.example.demo.exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ResponseBody
    @GetMapping("/products")
    public String find(@RequestParam(required = false) Category category) {
        List<Product> result;

        if(category == null){
            result = productRepository.findAll();
        } else {
            result = productRepository.findPerCategory(category);
        }

        String productsDetails = result.stream()
                .map(Product::toString)
                .collect(Collectors.joining("<br/>"));

        double sumPrices = sumPrice(result, category);
        return productsDetails + "<p>Suma cen powyższych produktów: " + sumPrices + " zł</p>"
                + "<a href=\"http://localhost:8080/\" target=\"_blank\">Powrót do strony głównej</a><br/>";
    }

    @ResponseBody
    @GetMapping("/list")
    public String getProductsPerCategory(@RequestParam Category category) {
        List<Product> productList = productRepository.findPerCategory(category);

        String productsDetails = productList.stream()
                .map(Product::toString)
                .collect(Collectors.joining("<br/>"));

        double sumPrices = sumPrice(productList, category);
        return productsDetails + "<p>Suma cen powyższych produktów: " + sumPrices + " zł</p>"
                + "<a href=\"http://localhost:8080/\" target=\"_blank\">Powrót do strony głównej</a><br/>";
    }

    public double sumPrice(List<Product> products, Category category) {
        return products.stream()
                .filter(product -> product.getCategory() == category)
                .mapToDouble(product -> product.getPrice())
                .sum();
    }

    @ResponseBody
    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam Double price, @RequestParam Category category) {
        Product product = new Product(name, price, category);
        productRepository.save(product);
        return "<p>Dodano nowy produkt</p>" + product.toString()
                + "<br/><a href=\"http://localhost:8080/\" target=\"_blank\">Powrót do strony głównej</a><br/>";
    }
}
