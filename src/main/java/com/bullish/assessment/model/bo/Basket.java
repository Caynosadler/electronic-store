package com.bullish.assessment.model.bo;

import java.util.HashMap;

public class Basket {
    private String id;
    private HashMap<String, Integer> products;

    public Basket(String basketId) {
        this.id = basketId;
       this.products = new HashMap<>();
    }

   public void addProductToBasket(String productId, int quantity) {
        if (products.containsKey(productId)) {
            quantity += products.get(productId);
            products.put(productId, quantity);
        } else {
            products.put(productId, quantity);
        }
   }

   public void removeProductFromBasket(String productId) {
        if (products.containsKey(productId)) {
           products.remove(productId);
        }
   }

   public HashMap<String, Integer> getProducts() {
        return products;
   }

   public String getId() {
        return this.id;
   }
}
