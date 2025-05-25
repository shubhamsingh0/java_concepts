package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportList {
    private Set<String> totalItems;
    private double totalPrice = 0.0;

    public ExportList(Product p1, Product p2) {
        this.totalItems.add(p1.getProductName());
        this.totalItems.add(p2.getProductName());
        this.totalPrice = this.totalPrice + p1.getPrice() + p2.getPrice();
    }
}
