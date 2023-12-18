package com.qa.opencart.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;
	private JavascriptExecutor js;

	
	public JavaScriptUtil(WebDriver driver) {
		this.driver=driver;
		js = (JavascriptExecutor)this.driver;
	}
	
	public String getTitleByJs() {
		return js.executeScript("return document.title").toString();
	}
	
	public String getURLByJs() {
		return js.executeScript("return document.URL").toString();
	}
	
	public void generateJSAlert(String mesg) {
		js.executeScript("alert('"+mesg+"')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
	}
	
	public void generateJSConfirmation(String mesg) {
		js.executeScript("confirm('"+mesg+"')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
	}
	
	public void generateJSPrompt(String mesg, String value) {
		js.executeScript("prompt('"+mesg+"')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
		alert.accept();
		
	}
	
	public void goBackWithJS() {  // back
		js.executeScript("history.go(-1)");
	}
	
	public void goForwardWithJS() {
		js.executeScript("history.go(1)");
	}
	public void pageRefreshWithJS() {
		js.executeScript("history.go(0)");
	}
	
	public String getPageInnerText() {
		return js.executeScript("return document.documentElement.innerText;").toString();
	}
	
	public void scrollPageDown() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}
	
	public void scrollPageDown(String height) {
		js.executeScript("window.scrollTo(0,'"+height+"');");
	}
	
	public void scrollPageUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight,0);");
	}
	
	public void scrollMiddlePageDown() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight/2);");
	}
	
	//scroll upto element only  - arguments[0] means scroll upto element..true means i want scroll down upto ele
	//false means i dont want scroll down down upto ele
	public void scrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	// zoom in and zoom out
	// zoompercenstage = 200%
	public void zoomChromeEdgeSafari(String zoomPercenstage) {
		String zoom = "document.body.style.zoom = '"+zoomPercenstage+"%'";
		js.executeScript(zoom);
	}
	
	//zoom in zoom out only work for firefox
	// give the zoompercenstage = 0.5
	public void zoomFirefox(String zoomPercenstage) {
		String zoom = "document.body.style.MozTransform =  scale("+zoomPercenstage+")'";
		js.executeScript(zoom);
	}
	
	public void drawBorder(WebElement element) {
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		for(int i = 0; i<10;i++) {
			changeColor("rgb(0,200,0)", element); //Green
			changeColor(bgcolor, element);
		}
	}
	
	public void changeColor(String color, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		try {
			Thread.sleep(20);
		}catch(InterruptedException e) {
			
		}
	}
	
	//Avoid this javascript click.. if nothing working normal and action click then use javascript click
	public void clickElementByJS(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}
	//use only for ID 
	public void sendKeysUsingWithId(String id, String value) {
		js.executeScript("document.getElementById('"+id +"').value='" + value + "'");
	}
}
