package org.example.billingservice;

import org.example.billingservice.entities.Bill;
import org.example.billingservice.entities.ProductItem;
import org.example.billingservice.model.Customer;
import org.example.billingservice.model.Product;
import org.example.billingservice.repository.BillRepository;
import org.example.billingservice.repository.ProductItemRepository;
import org.example.billingservice.service.CustomerRestClient;
import org.example.billingservice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@EnableFeignClients
@SpringBootApplication
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository ,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient){
        return args -> {
            Collection<Product> products = productRestClient.allProducts().getContent();
            long customerId = 1;
            Customer customer = customerRestClient.getCustomerById(customerId);
            if(customer == null) throw new RuntimeException("Customer Not Found");
            Bill bill = new Bill();
            bill.setBillDate(new Date());
            bill.setCustomerId(customerId);
            Bill saved = billRepository.save(bill);
            products.forEach(p->{
                ProductItem productItem = new ProductItem();
                productItem.setBill(saved);
                productItem.setQuantity(1+ new Random().nextInt(10));
                productItem.setPrice(p.getPrice());
                productItem.setDiscount(Math.random());
            });
        };
    }

}
