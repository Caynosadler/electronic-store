package com.bullish.assessment.services;

import com.bullish.assessment.dao.ProductDao;
import com.bullish.assessment.model.bo.DiscountDeal;
import com.bullish.assessment.model.bo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AdminServices {

    @Autowired
    ProductDao productDao;

    public void addProduct(String productId, String productName, double price) {
        Product product = new Product(productId, productName, price);
        productDao.addProduct(product);
    }

    public void removeProduct(String productId) {
        productDao.removeProduct(productId);
    }

    public void addDiscountDeal(String productId, int quantity, double percentage) {
        DiscountDeal discountDeal = new DiscountDeal(quantity, percentage);
        Product product = productDao.getProduct(productId);
        product.setDiscountDeal(discountDeal);
        productDao.addProduct(product);
    }

    public HashMap<String, Product> getProducts() {
        return productDao.getProductHashMap();
    }
}
