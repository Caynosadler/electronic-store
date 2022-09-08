package com.bullish.assessment.dao;

import com.bullish.assessment.model.bo.Basket;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class BasketDao {

    private HashMap<String, Basket> basketHashMap;

    public BasketDao() {
        this.basketHashMap = new HashMap<>();
    }

    public void addBasket(Basket basket) {
        basketHashMap.put(basket.getId(), basket);
    }

    public Basket getBasket(String basketId) {
        return basketHashMap.get(basketId);
    }

    public boolean basketExists(String basketId) {
        return basketHashMap.containsKey(basketId);
    }
}
