package com.bullish.assessment.services;

import com.bullish.assessment.dao.BasketDao;
import com.bullish.assessment.dao.ProductDao;
import com.bullish.assessment.model.bo.Basket;
import com.bullish.assessment.model.bo.DiscountDeal;
import com.bullish.assessment.model.bo.Product;
import com.bullish.assessment.model.bo.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerServices {

    @Autowired
    BasketDao basketDao;

    @Autowired
    ProductDao productDao;

    public Receipt calculateTotalPrice(Receipt receipt) {
        double totalPrice = 0;
        for (Map.Entry<String, Integer> entry: receipt.getPurchases().entrySet()) {
            int quantity = entry.getValue();
            // Calculate total price
            Product product = productDao.getProduct(entry.getKey());
            double cost = product.getPrice();
            totalPrice += cost * quantity;

            // Calculate discount and apply if quantity threshold is met
            DiscountDeal discountDeal = product.getDiscountDeal();
            if (discountDeal != null && quantity >= discountDeal.getQuantity()) {
                double discountAmount = discountDeal.getPercentage() * totalPrice;
                totalPrice -= discountAmount;
                receipt.addToDiscountDeals(discountDeal);
            }
        }

        receipt.setTotalPrice(totalPrice);
        return receipt;
    }

    public void addProductToBasket(String basketId, String productId, int quantity) {
        // Create new basket if it doesn't exist
        Basket basket;
        if (! basketDao.basketExists(basketId)) {
            basket = new Basket(basketId);
            basketDao.addBasket(basket);
        } else {
            basket = basketDao.getBasket(basketId);
        }

        basket.addProductToBasket(productId, quantity);
    }

    public void removeProductFromBasket(String basketId, String productId) {
        Basket basket = basketDao.getBasket(basketId);
        basket.removeProductFromBasket(productId);
    }

    public Receipt generateReceipt(String basketId) {
        Basket basket = basketDao.getBasket(basketId);
        Receipt receipt = new Receipt(basket);
        receipt = calculateTotalPrice(receipt);
        return receipt;
    }
}
