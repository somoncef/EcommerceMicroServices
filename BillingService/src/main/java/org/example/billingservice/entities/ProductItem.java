package org.example.billingservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.billingservice.model.Product;

@Entity @NoArgsConstructor
@AllArgsConstructor
@Builder @Data
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long productId;
    @ManyToOne
    private Bill bill;
    private double price;
    private double quantity;
    private double discount;
    @Transient
    private Product product;

}
