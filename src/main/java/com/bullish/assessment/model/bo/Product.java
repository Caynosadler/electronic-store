package com.bullish.assessment.model.bo;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String name;
    private double price;
    private DiscountDeal discountDeal;

    public Product(String productId, String productName, double price) {
        this.id = productId;
        this.name = productName;
        this.price = price;
    }
}
