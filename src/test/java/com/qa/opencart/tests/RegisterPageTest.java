package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetUp() { //pre conidtion class for register page - user must be registration page
		registerPage= loginPage.navigateToRegisterPage();
		
	}
	
	
	public String getRandomEmailID() {
		return "testautomation"+System.currentTimeMillis()+"@opencart.com";
		//OR
		//return "testautomation"+ UUID.randomUUID()+"opencart.com";
	}
	
	
	//DataProvide - same test run multiple times
//	@DataProvider
//	public Object[][] getUserRegData() {
//		return new Object[][] {
//			{"abc12", "xyz", "9876543213", "test@1236", "yes"},
//			{"sachin", "xyz", "9876543211", "test@1234", "no"},
//			{"pavan", "xyz", "9876543212", "test@1235", "yes"},
//		};
//	}
	
//	@Test(dataProvider = "getUserRegData")
//	public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {
//		boolean isRegDone = registerPage.userRegisteration
//				(firstName, lastName, getRandomEmailID(), telephone, password, subscribe);
//		Assert.assertTrue(isRegDone);
//	}
	
	
	@DataProvider
	public Object[][] getUserRegTestExcelData() {
		Object regData[][] =  ExcelUtil.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
		return regData;
	}
	
	
	
	
	//run= total rows
	//params = total rows
	@Test(dataProvider = "getUserRegTestExcelData")
	public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		boolean isRegDone = registerPage.userRegisteration
				(firstName, lastName, getRandomEmailID(), telephone, password, subscribe);
		Assert.assertTrue(isRegDone);
	}

}
