package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		
		//String browserName = System.getProperty("browser");

		System.out.println("browser name is :" + browserName);

		OptionsManager optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			// headless
			// if headless = true through confiq.properties then chrome will not be launch
			// and
			// optionsManager.getChromeOptions() - co
			// if headless = false though confiq.properties file then chrome will be launch
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			//every thread get new copy of chrome driver
			break;

		case "firefox":
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		default:
			System.out.println("please pass right browser name...." + browserName);
			throw new FrameworkException("No Browser Found");
		}
		/*
		 * normal driver
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		// url is key of properties file
		driver.get(prop.getProperty("url"));
		return driver;
		*/
		
		// thread local
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		// url is key of properties file
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	//for thread local concept
	public static WebDriver getDriver() {
		return tlDriver.get();  // local copy of driver
		
	}
	
	

	public Properties initProp() {

		// ./ - root of project
		// mvn clean install -Denv="qa"
		// mvn clean install
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("env name is: " + envName);

		try {
			if (envName == null) {
				System.out.println("your env is  null..hence running tests on QA env...");
				ip = new FileInputStream("./src/test/resourses/confiq/confiq.qa.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resourses/confiq/confiq.qa.properties");
					break;

				case "dev":
					ip = new FileInputStream("./src/test/resourses/confiq/confiq.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resourses/confiq/confiq.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resourses/confiq/confiq.uat.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resourses/confiq/confiq.properties");
					break;
				default:
					System.out.println("please pass the right env name..." + envName);
					throw new FrameworkException("wrong Env name: " + envName);
				}
			}
		} catch (FileNotFoundException e) {

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
