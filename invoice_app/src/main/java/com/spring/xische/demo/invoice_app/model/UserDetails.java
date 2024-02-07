package com.spring.xische.demo.invoice_app.model;

import java.time.LocalDate;

import com.spring.xische.demo.invoice_app.utils.UserTypes;

public class UserDetails {
	private String name;
	private String phoneNumber;
	private UserTypes userType;
	private LocalDate userSince;

	public UserDetails(String name, String phoneNumber, UserTypes userType, LocalDate userSince) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.userType = userType;
		this.userSince = userSince;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserTypes getUserType() {
		return userType;
	}

	public void setUserType(UserTypes userType) {
		this.userType = userType;
	}

	public LocalDate getUserSince() {
		return userSince;
	}

	public void setUserSince(LocalDate userSince) {
		this.userSince = userSince;
	}

	@Override
	public String toString() {
		return "UserDetails [name=" + name + ", phoneNumber=" + phoneNumber + ", userType=" + userType + ", userSince="
				+ userSince + "]";
	}

}