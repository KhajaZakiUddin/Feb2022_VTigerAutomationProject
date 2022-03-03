package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pojo.Credentials;
import com.qa.hubspot.utilities.AppConstants;
import com.qa.hubspot.utilities.ElementActions;

public class LoginPage extends BasePage {
	
	public WebDriver driver;
	ElementActions elementActions;
	
	
	//1. Creating the page objects/locators with By class
	 By username = By.xpath("//input[@name='username']");
	 By password = By.xpath("//input[@name='password']");
	 By loginBtn = By.xpath("//button[text()='Sign in']");
	 By logoHeader = By.xpath("//img[@class='mb-4']");
	 By errorMessage = By.xpath("//p[text()='Invalid credentials']");
	 
	//2. Create the Constructor of page class
	 public LoginPage(WebDriver driver){
		 this.driver = driver;
		 elementActions = new ElementActions(this.driver);
	 }
	
	 
	//3. Defining the Page Actions/Methods
	 
	 public String getPageTitle(){
		 return elementActions.doGetPageTitle(AppConstants.LOGIN_PAGE_TITLE);
	 }
	 
	 public boolean verifyLogoHeader(){
		 elementActions.waitForElementPresent(logoHeader);
		 return elementActions.doisDisplayed(logoHeader);
	 }
	 
	 public HomePage doLogin(Credentials credentials) {
		elementActions.waitForElementPresent(username);
		elementActions.doSendKeys(username, credentials.getEmailId());
		elementActions.doSendKeys(password, credentials.getPassword());
		elementActions.doClick(loginBtn);
		
		return new HomePage(driver);
		
	 }
	 
	 public boolean checkLoginErrorMessage(){
		return elementActions.doisDisplayed(errorMessage);
	 }

}
