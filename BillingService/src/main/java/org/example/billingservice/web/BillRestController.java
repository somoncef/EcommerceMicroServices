package org.example.billingservice.web;

import org.example.billingservice.entities.Bill;
import org.example.billingservice.entities.ProductItem;
import org.example.billingservice.model.Customer;
import org.example.billingservice.model.Product;
import org.example.billingservice.repository.BillRepository;
import org.example.billingservice.repository.ProductItemRepository;
import org.example.billingservice.service.CustomerRestClient;
import org.example.billingservice.service.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;
    @GetMapping(path = "/bills/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).orElse(null);
        if (bill == null) {
            throw new RuntimeException("Bill not found for id: " + id);
        }

        Customer customer = customerRestClient.getCustomerById(bill.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("Customer not found for id: " + bill.getCustomerId());
        }
        bill.setCustomer(customer);

        bill.getProductItems().forEach(productItem -> {
            Product product = productRestClient.getProductById(productItem.getProductId());
            if (product == null) {
                throw new RuntimeException("Product not found for id: " + productItem.getProductId());
            }
            productItem.setProduct(product);
        });

        return bill;
    }

    @GetMapping(path = "/fullBills/{id}/productItems")
    public List<ProductItem> getProductItems(@PathVariable(name = "id") Long billId) {
        try {
            System.out.println("Fetching product items for bill ID: " + billId);
            Bill bill = billRepository.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found"));
            List<ProductItem> productItems = productItemRepository.findByBillId(billId);
            System.out.println("Fetched product items: " + productItems);
            return productItems;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during fetching product items: " + e.getMessage());
        }
    }
}
