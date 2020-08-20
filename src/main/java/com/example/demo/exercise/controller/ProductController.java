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
    @GetMapping("/all-products")
    public String getAll(){
        List<Product> productList = productRepository.findAll();

        String productsDetails =  productList.stream()
                .map(Product::toString)
                .collect(Collectors.joining("<br/>"));

        String sumPrices = sumPrice(productList);
        return productsDetails + sumPrices
                + "<a href=\"http://localhost:8080/\" target=\"_blank\">Powrót do strony głównej</a><br/>";
    }

    @ResponseBody
    @GetMapping("/list")
    public String getProductsPerCategory(@RequestParam String category){
        List<Product> productList = productRepository.findPerCategory(Category.valueOf(category));

        String productsDetails =  productList.stream()
                .map(Product::toString)
                .collect(Collectors.joining("<br/>"));

        String sumPrices = sumPrice(productList);
        return productsDetails + sumPrices
                + "<a href=\"http://localhost:8080/\" target=\"_blank\">Powrót do strony głównej</a><br/>";
    }

    public String sumPrice(List<Product> products){
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return "<p>Suma cen powyższych produktów: " + sum + " zł</p>";
    }

    @ResponseBody
    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam Double price, @RequestParam String category) {
            Product product = new Product(name, price, Category.valueOf(category));
            productRepository.save(product);
            return "<p>Dodano nowy produkt</p>" + product.toString()
                    + "<br/><a href=\"http://localhost:8080/\" target=\"_blank\">Powrót do strony głównej</a><br/>";
    }
}
