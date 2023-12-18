package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductResultPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	/*
	@DataProvider
	public Object[][] getSearchData() {  //data provided always return 2D array
		return new Object[][] {
			{"MacBook","MacBook Pro", 4},
			{"MacBook","MacBook Air", 4},
			{"iMac","iMac", 3},
			{"samsung","Samsung SyncMaster 941BW", 1},

		};
	}
	
	 //4 times running coz 4 rows are there in getSerachData method
		@Test(dataProvider = "getSearchData")
		public void productImagesTest(String searchkey, String productName, int imageCount) {  
			
			searchResultPage = accPage.doSearch(searchkey);
			productInfoPage= searchResultPage.selectProduct(productName);
			Assert.assertEquals(productInfoPage.getProductImagesCount(),imageCount);
		}
	*/
	
	@DataProvider
	public Object[][] getSearchExcelTestData() {  //data provided always return 2D array
		Object[][] productData=ExcelUtil.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
		return productData;
	}
	
	// 4 times running coz 4 rows are there in getSerachData method
	@Test(dataProvider = "getSearchExcelTestData")
	public void productImagesTest(String searchkey, String productName, String imageCount) {  
		
		searchResultPage = accPage.doSearch(searchkey);
		productInfoPage= searchResultPage.selectProduct(productName);
		Assert.assertEquals(String.valueOf(productInfoPage.getProductImagesCount()),imageCount);
	}
	
	@Test
	public void productInfoTest() {
		searchResultPage = accPage.doSearch("MacBook");
		productInfoPage= searchResultPage.selectProduct("MacBook Pro");
		Map<String,String> productDetailsMap = productInfoPage.getProductDetails();
		
		System.out.println("The keys are " + productDetailsMap.keySet());
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		
		softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("extaxprice"), "$2,000.00");
		softAssert.assertAll();
	}
	
	/*
	//Not working
	//using excel data
	// check this URL = https://roytuts.com/deal-with-empty-or-blank-cell-in-excel-file-using-apache-poi/
	@DataProvider
	public Object[][] getProductInfoExcelTestData() {  //data provided always return 2D array
		Object[][] productInfoData=ExcelUtil.getTestData(AppConstants.PRODUCT_INFO_DATA_SHEET_NAME);
		return productInfoData;
	}
	
	@Test(dataProvider = "getProductInfoExcelTestData")
	public void productInfoTest(String searchkey, String productName, String brand, String availability, String productcode,
			String rewardpoints, String price, String extaxprice) {
		searchResultPage = accPage.doSearch(searchkey);
		productInfoPage= searchResultPage.selectProduct(productName);
		Map<String,String> productDetailsMap = productInfoPage.getProductDetails();
		
		System.out.println("The keys are " + productDetailsMap.keySet());
		softAssert.assertEquals(productDetailsMap.get(brand), productDetailsMap.values());
		softAssert.assertEquals(productDetailsMap.get(availability), productDetailsMap.values());
		softAssert.assertEquals(productDetailsMap.get(productcode), productDetailsMap.values());
		softAssert.assertEquals(productDetailsMap.get(rewardpoints), productDetailsMap.values());
		
		softAssert.assertEquals(productDetailsMap.get(price), productDetailsMap.values());
		softAssert.assertEquals(productDetailsMap.get(extaxprice), productDetailsMap.values());
		softAssert.assertAll();
	}
	*/
	//not working till here

}
