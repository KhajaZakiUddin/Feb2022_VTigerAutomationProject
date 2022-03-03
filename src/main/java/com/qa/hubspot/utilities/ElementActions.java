package com.qa.hubspot.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	
	public ElementActions(WebDriver driver){
		this.driver=driver;
		wait = new WebDriverWait(this.driver, AppConstants.DEFAULT_EXPLICIT_TIMEOUT);
		action = new Actions(this.driver);
	}
	
	
	public WebElement getElement(By locator){
		WebElement element = null;
		try{
			element = driver.findElement(locator);
		}catch(Exception e){
			System.out.println("Some exception occured while locating the WebElement : " +locator);
		}
		return element;
	}
	
	
	public void doClick(By locator){
		getElement(locator).click();	
	}
	
	public void doActionsClick(By locator){
		action.moveToElement(getElement(locator)).click().build().perform();
	}
	
	public void doSendKeys(By locator, String value){
		getElement(locator).sendKeys(value);
	}
	
	public void doActionsSendKeys(By locator, String value){
		action.sendKeys(getElement(locator), value).build().perform();;
	}
	
	public void doMoveToElement(By locator){
		action.moveToElement(getElement(locator)).build().perform();
	}
		
	
	public boolean doisDisplayed(By locator){
		return getElement(locator).isDisplayed();
	}
	
	public String doGetText(By locator){
		return getElement(locator).getText();
	}
	
	public String doGetPageTitle(String title){
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	public void waitForElementPresent(By locator){
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void waitForElementVisible(By locator){
		WebElement ele = getElement(locator);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementClickable(By locator){
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		
	}
	
	
	
	
	public void doAlertCheck(){
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
}
