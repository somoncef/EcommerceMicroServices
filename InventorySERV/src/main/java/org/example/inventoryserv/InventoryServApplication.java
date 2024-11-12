package org.example.inventoryserv;

import org.example.inventoryserv.ALL.Product;
import org.example.inventoryserv.ALL.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(null, "Computer Desk Top HP", 900));
            productRepository.save(new Product(null, "Printer Epson", 80));
            productRepository.save(new Product(null, "MacBook Pro Lap Top", 1800));
            productRepository.findAll().forEach(System.out::println);
        };
    }
}