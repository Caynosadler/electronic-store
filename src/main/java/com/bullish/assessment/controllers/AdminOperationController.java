package com.bullish.assessment.controllers;

import com.bullish.assessment.model.http.AddDiscountDealRequest;
import com.bullish.assessment.model.http.AdminOperationResponse;
import com.bullish.assessment.model.http.CreateNewProductRequest;
import com.bullish.assessment.model.http.RemoveProductRequest;
import com.bullish.assessment.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminOperationController {

    @Autowired
    AdminServices adminServices;

    @PostMapping("/createProduct")
    @ResponseBody
    public AdminOperationResponse createNewProduct(@RequestBody CreateNewProductRequest createNewProductRequest) {
        adminServices.addProduct(createNewProductRequest.getProductId(), createNewProductRequest.getProductName(),
                createNewProductRequest.getProductPrice());
        String response = String.format("Product %s created", createNewProductRequest.getProductId());
        AdminOperationResponse adminOperationResponse = new AdminOperationResponse();
        adminOperationResponse.setResponseText(response);
        return adminOperationResponse;
    }

    @PostMapping("/removeProduct")
    @ResponseBody
    public AdminOperationResponse removeProduct(@RequestBody RemoveProductRequest removeProductRequest) {
        adminServices.removeProduct(removeProductRequest.getProductId());
        String response = String.format("Product  %s removed", removeProductRequest.getProductId());
        AdminOperationResponse adminOperationResponse = new AdminOperationResponse();
        adminOperationResponse.setResponseText(response);
        return adminOperationResponse;
    }

    @PostMapping("/addDiscountDeals")
    @ResponseBody
    public AdminOperationResponse addDiscountDeals(@RequestBody AddDiscountDealRequest addDiscountDealRequest) {
        adminServices.addDiscountDeal(addDiscountDealRequest.getProductId(), addDiscountDealRequest.getQuantity(),
                addDiscountDealRequest.getPrice());
        String response = String.format("Discount deal added to Product %s", addDiscountDealRequest.getProductId());
        AdminOperationResponse adminOperationResponse = new AdminOperationResponse();
        adminOperationResponse.setResponseText(response);
        return adminOperationResponse;
    }
}
