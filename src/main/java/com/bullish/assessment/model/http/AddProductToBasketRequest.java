package com.bullish.assessment.model.http;

import lombok.Data;

@Data
public class AddProductToBasketRequest {
    private String basketId;
    private String productId;
    private int quantity;
}
