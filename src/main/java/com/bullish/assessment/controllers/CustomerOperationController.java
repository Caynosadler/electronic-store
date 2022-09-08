package com.bullish.assessment.controllers;

import com.bullish.assessment.model.bo.Receipt;
import com.bullish.assessment.model.http.*;
import com.bullish.assessment.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class CustomerOperationController {

    @Autowired
    CustomerServices customerServices;

    @PostMapping("/createProduct")
    @ResponseBody
    public CustomerOperationResponse addProductToBasket(@RequestBody AddProductToBasketRequest addProductToBasketRequest) {
        customerServices.addProductToBasket(addProductToBasketRequest.getBasketId(), addProductToBasketRequest.getProductId(),
                addProductToBasketRequest.getQuantity());

        CustomerOperationResponse customerOperationResponse = new CustomerOperationResponse();
        String response = String.format("Product %s added to Basket %s", addProductToBasketRequest.getProductId(),
                addProductToBasketRequest.getBasketId());
        customerOperationResponse.setResponseText(response);
        return customerOperationResponse;
    }

    @PostMapping("/removeProduct")
    @ResponseBody
    public CustomerOperationResponse removeProductFromBasket(@RequestBody RemoveProductFromBasketRequest removeProductFromBasketRequest) {
        customerServices.removeProductFromBasket(removeProductFromBasketRequest.getBasketId(), removeProductFromBasketRequest.getProductId());

        CustomerOperationResponse customerOperationResponse = new CustomerOperationResponse();
        String response = String.format("Product %s removed from Basket %s", removeProductFromBasketRequest.getProductId(),
                removeProductFromBasketRequest.getBasketId());
        customerOperationResponse.setResponseText(response);
        return customerOperationResponse;
    }

    @PostMapping("/getReceipt")
    @ResponseBody
    public GetReceiptResponse getReceipt(@RequestBody GetReceiptRequest getReceiptRequest) {
        Receipt receipt = customerServices.generateReceipt(getReceiptRequest.getBasketId());
        GetReceiptResponse getReceiptResponse = new GetReceiptResponse();
        getReceiptResponse.setTotalPrice(receipt.getTotalPrice());
        getReceiptResponse.setDiscountDeals(receipt.getDiscountDeals());
        getReceiptResponse.setPurchases(receipt.getPurchases());
        return getReceiptResponse;
    }
}
