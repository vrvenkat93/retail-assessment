package com.spring.xische.demo.invoice_app.model;

import java.math.BigDecimal;

import com.spring.xische.demo.invoice_app.utils.ProductTypeDetails;

public class ProductDetails {

	private ProductTypeDetails productType;

	private String productName;

	private BigDecimal price;

	public ProductDetails(ProductTypeDetails productType, String productName, BigDecimal price) {
		super();
		this.productType = productType;
		this.productName = productName;
		this.price = price;
	}

	public ProductTypeDetails getProductType() {
		return productType;
	}

	public void setProductType(ProductTypeDetails productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductDetails [productType=" + productType + ", productName=" + productName + ", price=" + price + "]";
	}

}