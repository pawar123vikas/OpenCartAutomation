package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;



public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By searchIcon = By.cssSelector("div#search button");
	private By search = By.name("search");
	private By accHeaders = By.cssSelector("div#content > h2");
	
	
	//page constructor
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	
	public String getAccountPageTitle() {
		String title= eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account page title:" + title);
		return title;
	}
	
	public String getAccountPageURL() {
		String url= eleUtil.waitForURLContains(AppConstants.ACCOUNT_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("account page url:" + url);
		return url;
	}

	//page actions/methods
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}
	
	public void logout() {
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}
	
	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	} 
	
	public List<String> getAccountHeaders() {
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElements(accHeaders, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headersValList = new ArrayList<String>(); 
		for(WebElement e: headersList) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	
	
	public SerachResultPage doSearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		return new SerachResultPage(driver);  //TDD(Test Driven Development)
	}
	
	
	
}
