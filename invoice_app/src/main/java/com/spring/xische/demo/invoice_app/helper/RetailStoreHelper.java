package com.spring.xische.demo.invoice_app.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.xische.demo.invoice_app.utils.ProductTypeDetails;
import com.spring.xische.demo.invoice_app.utils.UserTypes;
import com.spring.xische.demo.invoice_app.model.ProductDetails;
import com.spring.xische.demo.invoice_app.model.UserDetails;

@Component
public class RetailStoreHelper {

	private static final int YEARS_FOR_DISCOUNT = 2;

	private static final double EMPLOYEE_DISCOUNT_PERCENTAGE = 0.30;
	private static final double AFFILIATE_DISCOUNT_PERCENTAGE = 0.10;
	private static final double CUSTOMER_DISCOUNT_PERCENTAGE = 0.05;

	public BigDecimal calculateTotal(List<ProductDetails> items) {
		return items.stream().map(i -> i.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal calculateTotalPerType(List<ProductDetails> products, ProductTypeDetails type) {
		BigDecimal sum = new BigDecimal(0);

		if (type != null) {
			sum = products.stream().filter(i -> type.equals(i.getProductType())).map(i -> i.getPrice())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		}

		return sum;
	}

	public BigDecimal getUserDiscount(UserDetails user) {
		if (user == null) {
			throw new NullPointerException("User cannot be null");
		}

		BigDecimal discount = new BigDecimal(0);

		UserTypes type = user.getUserType();

		switch (type) {
		case EMPLOYEE:
			discount = BigDecimal.valueOf(EMPLOYEE_DISCOUNT_PERCENTAGE).setScale(2, RoundingMode.HALF_EVEN);
			break;

		case AFFILIATE:
			discount = BigDecimal.valueOf(AFFILIATE_DISCOUNT_PERCENTAGE).setScale(2, RoundingMode.HALF_EVEN);
			break;

		case CUSTOMER:
			if (isCustomerSince(user.getUserSince(), YEARS_FOR_DISCOUNT)) {
				discount = BigDecimal.valueOf(CUSTOMER_DISCOUNT_PERCENTAGE).setScale(2, RoundingMode.HALF_EVEN);
			}
			break;

		default:
			break;
		}

		return discount;
	}

	public boolean isCustomerSince(LocalDate registeredDate, long years) {
		Period period = Period.between(registeredDate, LocalDate.now());
		return period.getYears() >= years;
	}

	public BigDecimal calculateBillsDiscount(BigDecimal totalAmount, BigDecimal amount, BigDecimal discountAmount) {
		int value = totalAmount.divide(amount).intValue();
		return discountAmount.multiply(new BigDecimal(value));
	}

	public BigDecimal calculateDiscount(BigDecimal amount, BigDecimal discount) {
		if (discount.doubleValue() > 1.0) {
			throw new IllegalArgumentException("Discount cannot be more than 100%");
		}

		BigDecimal x = amount.multiply(discount);
		return amount.subtract(x);
	}

}