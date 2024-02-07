package com.spring.xische.demo.invoice_app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.xische.demo.invoice_app.services.InvoiceService;
import com.spring.xische.demo.invoice_app.utils.ProductTypeDetails;
import com.spring.xische.demo.invoice_app.utils.UserTypes;
import com.spring.xische.demo.invoice_app.model.InvoiceDetails;
import com.spring.xische.demo.invoice_app.model.ProductDetails;
import com.spring.xische.demo.invoice_app.model.UserDetails;

@SpringBootTest
class RetailStoreApplicationTests {

	@Autowired
	public InvoiceService invoiceService;

	@Autowired
	public InvoiceDetails invoice;

	@Test
	void invoiceCalculationUserIsEmployee() {
		List<ProductDetails> products = addProductDetails();
		invoice.setProducts(products);
		BigDecimal amount = invoiceService.invoiceCalulation(
				new UserDetails("Venkat", "+91-7708163277", UserTypes.EMPLOYEE, LocalDate.now()), invoice);
		assertEquals(1280, amount.doubleValue(), 0, " Bill Amount for this User");
	}

	@Test
	void invoiceCalculationUserIsAffiliate() {
		List<ProductDetails> products = addProductDetails();
		invoice.setProducts(products);
		BigDecimal amount = invoiceService.invoiceCalulation(
				new UserDetails("Raj", "+91-9874561230", UserTypes.AFFILIATE, LocalDate.now()), invoice);
		assertEquals(1630, amount.doubleValue(), 0, " Bill Amount for this User");
	}

	@Test
	void invoiceCalculationUserIsRecentCustomer() {
		List<ProductDetails> products = addProductDetails();
		invoice.setProducts(products);
		LocalDate joinDate = LocalDate.now().minusYears(1);
		BigDecimal amount = invoiceService
				.invoiceCalulation(new UserDetails("Lax", "+91-9600629439", UserTypes.CUSTOMER, joinDate), invoice);
		assertEquals(1805, amount.doubleValue(), 0, " Bill Amount for this User");
	}

	@Test
	void invoiceCalculationUserIsOldCustomer() {
		List<ProductDetails> products = addProductDetails();
		invoice.setProducts(products);
		LocalDate joinDate = LocalDate.now().minusYears(5);
		BigDecimal amount = invoiceService
				.invoiceCalulation(new UserDetails("Anitha", "+91-9444408877", UserTypes.CUSTOMER, joinDate), invoice);
		assertEquals(1717.5, amount.doubleValue(), 0, " Bill Amount for this User");
	}

	@Test
	void invoiceCalculationUserIsAffiliate_NonGroceries() {
		List<ProductDetails> products = addProductDetails_NonGroceries();
		invoice.setProducts(products);
		BigDecimal amount = invoiceService.invoiceCalulation(
				new UserDetails("Venkat", "+91-7708163277", UserTypes.AFFILIATE, LocalDate.now()), invoice);
		assertEquals(1490, amount.doubleValue(), 0, " Bill Amount for this User");
	}

	@Test
	void invoiceCalculationUserIsAffiliate_GroceriesOnly() {

		List<ProductDetails> products = addProductDetails_GroceriesOnly();
		invoice.setProducts(products);
		BigDecimal amount = invoiceService.invoiceCalulation(
				new UserDetails("Hareesh", "+91-9875648965", UserTypes.AFFILIATE, LocalDate.now()), invoice);
		assertEquals(155, amount.doubleValue(), 0, " Bill Amount for this User");
	}

	private List<ProductDetails> addProductDetails_GroceriesOnly() {
		List<ProductDetails> products = new ArrayList<ProductDetails>();
		products.add(new ProductDetails(ProductTypeDetails.GROCERY, "Berries", new BigDecimal(50.0)));
		products.add(new ProductDetails(ProductTypeDetails.GROCERY, "Salt", new BigDecimal(10.0)));
		products.add(new ProductDetails(ProductTypeDetails.GROCERY, "Apple", new BigDecimal(100.0)));
		return products;
	}

	private List<ProductDetails> addProductDetails_NonGroceries() {
		List<ProductDetails> products = new ArrayList<ProductDetails>();
		products.add(new ProductDetails(ProductTypeDetails.CLOTHING, "Jeans", new BigDecimal(1000.0)));
		products.add(new ProductDetails(ProductTypeDetails.ELECTRONICS, "Pendrive", new BigDecimal(500.0)));
		products.add(new ProductDetails(ProductTypeDetails.COSMETICS, "Perfume", new BigDecimal(250.0)));
		return products;
	}

	private List<ProductDetails> addProductDetails() {
		List<ProductDetails> products = new ArrayList<ProductDetails>();
		products.add(new ProductDetails(ProductTypeDetails.GROCERY, "Berries", new BigDecimal(50.0)));
		products.add(new ProductDetails(ProductTypeDetails.CLOTHING, "Jeans", new BigDecimal(1000.0)));
		products.add(new ProductDetails(ProductTypeDetails.ELECTRONICS, "Pendrive", new BigDecimal(500.0)));
		products.add(new ProductDetails(ProductTypeDetails.COSMETICS, "Perfume", new BigDecimal(250.0)));
		products.add(new ProductDetails(ProductTypeDetails.GROCERY, "Apple", new BigDecimal(100.0)));
		return products;
	}
}