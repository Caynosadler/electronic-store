package com.bullish.assessment.model.http;

import lombok.Data;

@Data
public class RemoveProductFromBasketRequest {
    private String basketId;
    private String productId;
}
