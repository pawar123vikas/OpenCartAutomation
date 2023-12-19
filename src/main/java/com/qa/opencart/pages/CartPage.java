package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class CartPage {
	
	private By cart = By.id("cart");
	private By page = By.id("page");

	public void getCart() {
		System.out.println("cart is added");
	}
}
