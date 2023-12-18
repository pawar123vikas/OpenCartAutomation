package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SerachResultPage;

public class BaseTest {
	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountPage accPage;
	protected SerachResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	protected SoftAssert softAssert;
	
	@Parameters({"browser"})
	@BeforeTest
	public void sepUp(String browserName) {
		df = new DriverFactory();          // initilize the df object for calling driver factory method
		prop =df.initProp();  //Using df object call the driver method for initlizing the prop
		
		//
		if(browserName!=null) {   //browser name is coming from xml file
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);  //all prop compoent sent initdriver method launch the browser &URL called call by refrence
		loginPage = new LoginPage(driver);  // once initilize the driver then login the application
		softAssert = new SoftAssert();    
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
