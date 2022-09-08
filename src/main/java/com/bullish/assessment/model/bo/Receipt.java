package com.bullish.assessment.model.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Data
public class Receipt {
    private String id;
    private HashMap<String, Integer> purchases;
    private List<DiscountDeal> discountDeals;
    private double totalPrice;

    public Receipt(Basket basket) {
        this.id = UUID.randomUUID().toString();
        this.purchases = basket.getProducts();
        this.discountDeals = new ArrayList<>();
    }

    public void addToDiscountDeals(DiscountDeal discountDeal) {
        discountDeals.add(discountDeal);
    }
}
