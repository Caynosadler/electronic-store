package com.bullish.assessment.dao;

import com.bullish.assessment.model.bo.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ProductDao {
    private HashMap<String, Product> productHashMap;

    public ProductDao() {
        this.productHashMap = new HashMap<>();
    }

    public void addProduct(Product product) {
        this.productHashMap.put(product.getId(), product);
    }

    public Product getProduct(String productId) {
        return productHashMap.get(productId);
    }

    public void removeProduct(String productId) {
        if (productHashMap.containsKey(productId)) {
            productHashMap.remove(productId);
        }
    }

    public boolean productExists(String productId) {
        return productHashMap.containsKey(productId);
    }

    public HashMap<String, Product> getProductHashMap() {
        return productHashMap;
    }
}
