package com.bullish.assessment.model.bo;

import lombok.Data;

import java.util.UUID;

@Data
public class DiscountDeal {
    private String id;
    private int quantity;
    private double percentage;

    public DiscountDeal(int quantity, double percentage) {
        this.id = UUID.randomUUID().toString();
        this.quantity = quantity;
        this.percentage = percentage;
    }
}
