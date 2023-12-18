package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exception.FrameworkException;



public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	//used when string locator
	public By getBy(String locatorType, String locatorValue) {

		By by = null;
		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by = By.id(locatorValue);
			break;
		case "name":
			by = By.name(locatorValue);
			break;
		case "class":
			by = By.className(locatorValue);
			break;
		case "xpath":
			by = By.xpath(locatorValue);
			break;
		case "cssSelector": 
			by = By.cssSelector(locatorValue);
			break;
		case "linktext":
			by = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locatorValue);
			break;
		case "tag":
			by = By.tagName(locatorValue);
			break;
		default:
			System.out.println("wrong locator type is passed" + locatorType);
			throw new FrameworkException("wrong locator type");
			
		}
		return by;

	}

	public void doSendkeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	//String based locator
	// locatorType = "id" locatorValue = "input-email" value = "test@gmail.com"
	public void doSendkeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);
	}

	//
	public WebElement getElement(By locator) {

		return driver.findElement(locator);
	}
	
	public WebElement getElement(String locatorType, String locatorValue) {

		return driver.findElement(getBy(locatorType,locatorValue));
	}
	
	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}
	
	//To get a text of webElement using by locator
	public  String getElementText(By locator) {
		return getElement(locator).getText();
	}
	//To get a text of webElement using string 
	public  String getElementText(String locatorType, String locatorValue) {
		return getElement(locatorType, locatorValue).getText();
	}
	
	
	//To get a lists attribute using by locator
	public  String doGetElementAttribute(By locator, String attrname) {
		return getElement(locator).getAttribute(attrname);
	}
	
	//Start FindElementsClass
	//write a function capture text of all the page links and return List<String>.
		public  List<String> getElementsTextList(By locator) {
			//selenium always giving you list of webelements. not giving the string we have create list of string
			List<WebElement> eleList = getElements(locator); 
			List<String> eleTextList = new ArrayList<String>(); //Physical capacity =0 
			for(WebElement e: eleList) {
				String text = e.getText();
				
				if(text.length()!=0) {
					eleTextList.add(text);
				}
			}
			return eleTextList;
		}
		
		//Write a function capture specific attribute from the list 
		public  List<String> getElementsAttributeList(By locator, String attributeName) {
			List<WebElement> eleList = getElements(locator); 
			List<String> eleArrList = new ArrayList<String>(); //Physical capacity =0 
			for(WebElement e : eleList) {
				String arrValue = e.getAttribute(attributeName);
				eleArrList.add(arrValue);
			}
			return eleArrList;
		}
		//Start FindElementsClass ends
		
		// return number of WebElements for tag 
		public  int getElementsCount(By locator) {
			return getElements(locator).size();
		}
		
		// return By locator list of webelements
		public  List<WebElement> getElements(By locator) {
			return driver.findElements(locator);
		}
		
		//GoogleSearch java class here, 
		//1. We enter the text on the google search box field - searchfield means find the search box
		//2. searchKey will be coming -- seachkey is suppose - selenium automation 
		//3. After searchkey suggestion coming for seleinum automation
		//4. then gettext we want to click on (interview questions)
		
		public  void Search(By searchField, By suggestions, String searchKey, String suggName) throws InterruptedException {
			
			doSendkeys(searchField, searchKey);
			Thread.sleep(3000);// 3secs
			
			List<WebElement> suggList =getElements(suggestions);
			System.out.println(suggList.size());
			
			for(WebElement e: suggList) {
				String text = e.getText();
				System.out.println(text);
				if(text.contains(suggName)) {
					e.click();
					break;
				}
			}
		}

		
// click on the one of link 
public  void clickOnElement(By locator,String eleText) {
	List<WebElement> eleList = getElements(locator);
	System.out.println(eleList.size());
	
	for(WebElement e: eleList) {
		String text = e.getText();
		System.out.println(text);
		if(text.contains(eleText)) {
			e.click();
			break;
		}
	}
	
  }

	// check single element present - logo
public  boolean checkSingleElementIsPresent(By locator) {
	return driver.findElements(locator).size() == 1 ? true : false;
}
	
// check multiple element present
public  boolean checkElementIsPresent(By locator) {
return driver.findElements(locator).size() >= 1 ? true : false;
}

//check multiple element present
public  boolean checkElementIsPresent(By locator, int totalElements) {
return driver.findElements(locator).size() == totalElements ? true : false;
}

	//**********************Select Drop Down utils**************************************//

private Select createSelect(By locator) {
	Select select = new Select(getElement(locator));
	return select;
}

public  void doSelectDropDownByIndex(By locator, int index) {
	createSelect(locator).selectByIndex(index);
}

public  void doSelectDropDownByVisibleText(By locator, String visibleText) {
	createSelect(locator).selectByVisibleText(visibleText);
}

public void doSelectDropDownByValue(By locator, String value) {
	createSelect(locator).selectByValue(value);
}

public  int getDropDownOptionsCount(By locator) {
	return createSelect(locator).getOptions().size();
}

public  List<String> getDropDownOption(By locator) {
	
	List<WebElement> optionsList = createSelect(locator).getOptions();
	List<String> optionsTextList = new ArrayList<String>();
	
	for(WebElement e: optionsList) {
		String text = e.getText();
		optionsTextList.add(text);
	}
	return optionsTextList;

}




public  void selectDropDownOption(By locator, String dropDownValue) {
	
	List<WebElement> optionsList = createSelect(locator).getOptions();
	System.out.println(optionsList.size());   // 233 total number of options available
	
	for(WebElement e: optionsList) {
		String text = e.getText();
		System.out.println(text);
		
		if(text.equals(dropDownValue)) {
			e.click();
			break;
		}
					
	}

}

//without select class

public  void selectDropDownValue(By locator, String value) {
	List<WebElement> optionList = getElements(locator);
	for(WebElement e: optionList) {
		String text = e.getText();
		if(text.equals(value)) {
			e.click();
			break;
		}
	}

}


public  boolean isDropDownMultiple(By locator) {
	return createSelect(locator).isMultiple() ? true :false;
}


/**
 * This is method used to select the values from the drop down. it select
 * 1. single selection
 * 2. Multiple selection
 * 3. All selection: Please pass "all" as value to select all the values
 * @param locator
 * @param values
 */
public  void selectDropDownMultipleValues(By locator,By optionLocator, String... values ) {
	if(isDropDownMultiple(locator)) {
		
		if(values[0].equalsIgnoreCase("all")) {
			List<WebElement> OptionsList = getElements(optionLocator);
			for(WebElement e: OptionsList) {
				e.click();
			}
		}
		else {
		for(String value:values) {
			createSelect(locator).selectByVisibleText(value);
			}
		}

	}
}



//******************************Actions Util*******************
// single menu item
public  void twoLevelMenuHandle(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
	Actions act = new Actions(driver);
	act.moveToElement(getElement(parentMenuLocator)).build().perform();
	Thread.sleep(1000);
	doClick(childMenuLocator);
}

//multi menu item
public  void fourLevelMenuHandle(By parentMenuLocator, By firstChildMenuLocator, By SecondChidlMenuLocator
		, By ThirdChildMenuLocator) throws InterruptedException {

	Actions act = new Actions(driver);
	
	
	doClick(parentMenuLocator);
	
	Thread.sleep(1000);
	act.moveToElement(getElement(firstChildMenuLocator)).build().perform();
	Thread.sleep(1000);
	act.moveToElement(getElement(SecondChidlMenuLocator)).build().perform();
	Thread.sleep(1000);
	doClick(ThirdChildMenuLocator);
}

public  void doActionsSendkeys(By locator, String value) {
	Actions act = new Actions(driver);
	act.sendKeys(getElement(locator), value).perform();
}

public void doActionsClick(By locator) {
	Actions act = new Actions(driver);
	act.click(getElement(locator)).perform();
}


public  void doActionsSendKeysWithPause(By locator, String value) {
	Actions act = new Actions(driver);
	char[] val = value.toCharArray();
	for(char c: val) {   // pause - i want one by character with pause
		act.sendKeys(getElement(locator), String.valueOf(c)).pause(500).build().perform();
}
	
}

	//***************************wait Utils**************************************//
/*
 * An expectation for checking that an element is present on the DOM of a page. 
 * This does not necessarily mean that the element is visible.
 * element is present in DOM or not checking.
 */
public  WebElement waitForPresentOfElement(By locator, int timeout) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}

/*
 * An expectation for checking that an element is present on the DOM of a page. 
 * This does not necessarily mean that the element is visible.
 * element is present in DOM or not checking.
 */

public  WebElement waitForPresentOfElement(By locator, int timeout, int intervalTime) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofSeconds(intervalTime));
	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}


/*
 * An expectation for checking that an element is present on the DOM of a page and visible.
 * Visibility means that the element is not only displayed but also has a height and width that 
 * is greater than 0.
 * element is present in DOM and page or not and also checking the height and width
 * is greater than 0
 */
public  WebElement waitForVisibilityOfElement(By locator, int timeout) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
}


public  WebElement waitForVisibilityOfElement(By locator, int timeout, int intervalTime) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofSeconds(intervalTime));
	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
}

public  void doClickWithWait(By locator, int timeout) {
	waitForVisibilityOfElement(locator, timeout).click();
}

public  void doSendKeysWithWait(By locator, String value, int timeout) {
	waitForVisibilityOfElement(locator, timeout).sendKeys(value);
}

/*
 * An expectation for checking that all elements present on the web page that match the locator are visible. 
 * Visibility means that the elements are not only displayed but also have a height and width that is 
 * greater than 0.
 */

public  List<WebElement> waitForVisibilityOfElements(By locator, int timeout) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
}

/*
 * An expectation for checking that there is at least one element present on a web page.
 */
public  List<WebElement> waitForPresentOfElements(By locator, int timeout) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
}

//title fraction value checking means About Us checking only About

	public  String waitForTitleContains(String titleFraction, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
			if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
			}
		}
		catch(TimeoutException e) {
			System.out.println(titleFraction + "title value is not present....");
			e.printStackTrace();
		}
		return null;
	}
	
	//title exact value checking means About Us checking only About Us
	public  String waitForTitleIs(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
			if(wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
			}
		}
		catch(TimeoutException e) {
			System.out.println(title + "title value is not present....");
			e.printStackTrace();
		}
		return null;
	}
	
	public  String waitForURLContains(String urlFraction, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
			if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
			}
		}
		catch(TimeoutException e) {
			System.out.println(urlFraction + "Url value is not present....");
			e.printStackTrace();
		}
		return null;
	}
	
	public  String waitForURLToBe(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
			if(wait.until(ExpectedConditions.urlToBe(url))) {
			return driver.getCurrentUrl();
			}
		}
		catch(TimeoutException e) {
			System.out.println(url + "Url value is not present....");
			e.printStackTrace();
		}
		return null;
	}
	
	public  Alert waitForJSAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public  void acceptJSAlert(int timeout) {
		waitForJSAlert(timeout).accept();
	}
	
	public  void dismissJSAlert(int timeout) {
		waitForJSAlert(timeout).dismiss();
	}

	public  String getJSAlertText(int timeout) {
		return waitForJSAlert(timeout).getText();
	}
	
	public  void enterValueOnJSAlert(int timeout, String value) {
		waitForJSAlert(timeout).sendKeys(value);
	}
	
	public  void waitForFrameByLocator(By frameLocator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public  void waitForFrameByIndex(int frameIndex, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	public  void waitForFrameByIDOrName(String IDOrName, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
	}
	
	public  void waitForFrameByElement(WebElement frameElement, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	public  boolean checkNewWindowExist(int timeout, int expectedNumberOfWindows) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
		if(wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows))) {
			return true;
		}
		}
		catch(TimeoutException e) {
			System.out.println("number of windows not same");
		}
		return false;
	}
	
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 */
	
	public void clickElementWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}
		catch(TimeoutException e) {
			System.out.println("element is not clickable or enabled");
		}

	}
	
	public  WebElement waitForElementWithFluentWait(By locator, int timeout, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(intervalTime))
				.withMessage("--timeout is done...element is still not found")
				.ignoring(NoSuchElementException.class)
				.ignoring(ElementNotInteractableException.class);
				
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForFrameWithFluentWait(String frameIDORName, int timeout, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(intervalTime))
				.withMessage("--timeout is done...element is still not found")
				.ignoring(NoSuchFrameException.class);

		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIDORName));
	}
	
	public Alert waitForJSAlertWithFluentWait(int timeout, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(intervalTime))
				.withMessage("--timeout is done...alert is  not appeared")
				.ignoring(NoAlertPresentException.class);

		 return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	//********************custom wait****************************************//
	
	public  WebElement retryingElement(By locator, int timeout) {
		WebElement element = null;
		int attempts = 0;
		
		while(attempts < timeout) {
			try {
			element = getElement(locator);
			System.out.println("element is found..." +  locator + " in attemp " + attempts);
			break;
			}
			catch(NoSuchElementException e) {
				System.out.println("element is not found.." +  locator +" in attemp " + attempts);
				try {
					Thread.sleep(500);  // next will attempt after 500 milli second  -- default polling time
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		
		if(element==null) {
			System.out.println("element is not found...tried for " + timeout + "times" + 
					"with the interval of " + 500 + " milli seconds " );
			throw new FrameworkException("No Such Element:");
		}
		return element;
	}
	
	
	// every 2 seconds it will check
	public  WebElement retryingElement(By locator, int timeout, int intervalTime) {
		WebElement element = null;
		int attempts = 0;
		
		while(attempts < timeout) {
			try {
			element = getElement(locator);
			System.out.println("element is found..." +  locator + " in attemp " + attempts);
			break;
			}
			catch(NoSuchElementException e) {
				System.out.println("element is not found.." +  locator +" in attemp " + attempts);
				try {
					Thread.sleep(intervalTime);  //next will attempt after 500 milli second-- custom polling time
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		
		if(element==null) {
			System.out.println("element is not found...tried for " + timeout + "times" + 
					"with the interval of " + 500 + " milli seconds " );
			throw new FrameworkException("No Such Element:");
		}
		return element;
	}
	
	public boolean isPageLoaded(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'")).toString();
		return Boolean.parseBoolean(flag);
	}
	

}
