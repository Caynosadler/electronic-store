package com.bullish.assessment.model.http;

import lombok.Data;

@Data
public class CreateNewProductRequest {
    private String productId;
    private String productName;
    private double productPrice;
}
