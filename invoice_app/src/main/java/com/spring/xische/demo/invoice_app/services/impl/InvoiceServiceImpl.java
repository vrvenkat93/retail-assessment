package com.spring.xische.demo.invoice_app.services.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.xische.demo.invoice_app.services.InvoiceService;
import com.spring.xische.demo.invoice_app.utils.ProductTypeDetails;
import com.spring.xische.demo.invoice_app.helper.RetailStoreHelper;
import com.spring.xische.demo.invoice_app.model.InvoiceDetails;
import com.spring.xische.demo.invoice_app.model.UserDetails;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	public RetailStoreHelper helper;

	@Override
	public BigDecimal invoiceCalulation(UserDetails user, InvoiceDetails invoice) {
		BigDecimal totalAmount = helper.calculateTotal(invoice.getProducts());
		BigDecimal groceryAmount = helper.calculateTotalPerType(invoice.getProducts(), ProductTypeDetails.GROCERY);
		BigDecimal nonGroceryAmount = totalAmount.subtract(groceryAmount);
		BigDecimal userDiscount = helper.getUserDiscount(user);
		BigDecimal billsDiscount = helper.calculateBillsDiscount(totalAmount, new BigDecimal(100), new BigDecimal(5));
		if (nonGroceryAmount.compareTo(BigDecimal.ZERO) > 0) {
			nonGroceryAmount = helper.calculateDiscount(nonGroceryAmount, userDiscount);
		}
		return groceryAmount.add(nonGroceryAmount).subtract(billsDiscount);
	}
}