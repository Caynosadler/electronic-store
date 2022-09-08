package com.bullish.assessment;

import com.bullish.assessment.controllers.AdminOperationController;
import com.bullish.assessment.controllers.CustomerOperationController;
import com.bullish.assessment.model.bo.Product;
import com.bullish.assessment.model.bo.Receipt;
import com.bullish.assessment.services.AdminServices;
import com.bullish.assessment.services.CustomerServices;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AssessmentApplicationTests {

	@Autowired
	private AdminOperationController adminOperationController;

	@Autowired
	private CustomerOperationController customerOperationController;

	@Autowired
	private CustomerServices customerServices;

	@Autowired
	AdminServices adminServices;

	@BeforeAll
	public void setup() {
		initProducts();
	}

	@Test
	public void contextLoads() {
		assertThat(adminOperationController).isNotNull();
		assertThat(customerOperationController).isNotNull();
		assertThat(customerServices).isNotNull();
		assertThat(adminServices).isNotNull();
		initProducts();
	}

	@Test
	public void testAdminOperations() {
		initProducts();
		adminServices.removeProduct("02");
		HashMap<String, Product> productList = adminServices.getProducts();
		assertThat(productList.size()).isEqualTo(3);
	}

	@Test
	public void testCustomerOperations() {
		// Test new basket is created on add first product
		customerServices.addProductToBasket("01", "02", 3);
		customerServices.addProductToBasket("01", "01", 5);

		customerServices.addProductToBasket("02", "04", 10);
		customerServices.addProductToBasket("02", "01", 2);
		customerServices.addProductToBasket("02", "03", 4);

		// Remove product from basket
		customerServices.removeProductFromBasket("02", "01");

		// Calculate receipt
		Receipt basket01 = customerServices.generateReceipt("01");
		Receipt basket02 = customerServices.generateReceipt("02");

		assertThat(basket01.getTotalPrice()).isEqualTo(1700.0);
		assertThat(basket01.getPurchases().size()).isEqualTo(2);

		assertThat(basket02.getTotalPrice()).isEqualTo(3540.0);
		assertThat(basket02.getPurchases().size()).isEqualTo(2);
		assertThat(basket02.getDiscountDeals().size()).isEqualTo(1);
	}

	private void initProducts() {
		// Create New product
		adminServices.addProduct("01", "Product A", 220);
		adminServices.addProduct("02", "Product B", 200);
		adminServices.addProduct("03", "Product C", 270);
		adminServices.addProduct("04", "Product D", 300);

		// Add Discount Deal
		adminServices.addDiscountDeal("03", 2, 0.5);
	}
}
