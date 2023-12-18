package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(),AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accPage.getAccountPageURL().contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutIsExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void isSearchFieldExistTest() {
		Assert.assertTrue(accPage.isSearchFieldExist());
	}
	
	@Test
	public void accAccPageHeadersCountTest() {
		List<String>  actAccPageHeadersList = accPage.getAccountHeaders();
		System.out.println(actAccPageHeadersList);
		Assert.assertEquals(actAccPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADER_COUNT);
	}
	
	@Test
	public void accAccPageHeadersTest() {
		List<String>  actAccPageHeadersList = accPage.getAccountHeaders();
		System.out.println(actAccPageHeadersList);
		//sort the actual list
		//sort the exepcted list
		//compare
		Assert.assertEquals(actAccPageHeadersList,AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	@Test
	public void searchTest() {
		searchResultPage=accPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		String actProductHeader =productInfoPage.getProductHeaderName();
		Assert.assertEquals(actProductHeader, "MacBook Pro");
		
	}

}
