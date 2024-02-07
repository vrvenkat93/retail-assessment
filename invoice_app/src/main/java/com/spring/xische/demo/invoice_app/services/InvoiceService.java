package com.spring.xische.demo.invoice_app.services;

import java.math.BigDecimal;

import com.spring.xische.demo.invoice_app.model.InvoiceDetails;
import com.spring.xische.demo.invoice_app.model.UserDetails;

public interface InvoiceService {

	/**
	 * This method calculate discount for given user and bill
	 * 
	 * @param user - UserDetails
	 * @param bill - InvoiceDetails
	 * @return Double - payable amount for the user
	 */
	BigDecimal invoiceCalulation(UserDetails user, InvoiceDetails invoice);

}