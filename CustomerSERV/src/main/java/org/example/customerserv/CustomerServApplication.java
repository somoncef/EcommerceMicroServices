package org.example.customerserv;

import org.example.customerserv.config.CustomerConfigParams;
import org.example.customerserv.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.example.customerserv.entity.Customer;
@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository ){
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("Mohamed").email("med@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Imane").email("imane@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Yassine").email("yassine@gmail.com")
                    .build());
        };
    }

}