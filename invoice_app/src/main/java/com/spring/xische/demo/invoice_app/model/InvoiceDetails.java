package com.spring.xische.demo.invoice_app.model;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDetails {

	private List<ProductDetails> products;

	@Autowired
	public InvoiceDetails() {

	}

	public InvoiceDetails(List<ProductDetails> products) {
		super();
		this.products = products;
	}

	public List<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetails> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		return Objects.hash(products);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceDetails other = (InvoiceDetails) obj;
		return Objects.equals(products, other.products);
	}

	@Override
	public String toString() {
		return "InvoiceDetails [products=" + products + "]";
	}
}