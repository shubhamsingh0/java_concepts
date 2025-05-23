package com.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Product {
    private String productName;
    private double price;
    private List<Timestamp> orderModificationTimestamp;
    private List<Double> prices;
}
