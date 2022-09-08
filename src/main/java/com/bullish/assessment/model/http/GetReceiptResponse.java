package com.bullish.assessment.model.http;

import com.bullish.assessment.model.bo.DiscountDeal;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class GetReceiptResponse {
    private double totalPrice;
    private List<DiscountDeal> discountDeals;
    private HashMap<String, Integer> purchases;
}
