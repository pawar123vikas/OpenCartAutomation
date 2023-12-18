package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//encapsulation
	//By locators : Object repository
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	private By register = By.linkText("Register");
//	private By unsucessfulMessg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
//	private By search = By.name("search");
//	private By unSucssSearchMesg = By.xpath("//h2[normalize-space()='Products meeting the search criteria']");
	
	//page constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//page actions/methods: 
	public String getLoginPageTitle() {
		String title= eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("login page title:" + title);
		return title;
	}
	
	public String getLoginPageURL() {
		String url= eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("login page url:" + url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public AccountPage doLogin(String username, String pwd) {
		System.out.println("creds are: " + username + ":" + pwd);
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		eleUtil.doSendkeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountPage(driver);
	}
	/*
	public boolean doLoginNegative(String username, String pwd) {
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		eleUtil.doSendkeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		String unsuccMesg = eleUtil.waitForVisibilityOfElement(unsucessfulMessg, AppConstants.LONG_DEFAULT_WAIT).getText();
		System.out.println(unsuccMesg);
		if(unsuccMesg.contains(AppConstants.USER_LOGIN_UNSUCCESS_MESSG)) {
			eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
			eleUtil.waitForVisibilityOfElement(password, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		}
		return true;
	}
	
	public boolean doSearchWithoutText() {
		
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		eleUtil.doClick(loginBtn);
		
		String unsuccSearchMesg = eleUtil.waitForVisibilityOfElement(unSucssSearchMesg, AppConstants.LONG_DEFAULT_WAIT).getText();
		System.out.println(unsuccSearchMesg);
		return unsuccSearchMesg.contains(AppConstants.USER_SEARCH_UNSUCCESS_MESSG);
		
	}
	*/	
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(register, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new RegisterPage(driver);
	}
}
