package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utilities.AppConstants;
import com.qa.hubspot.utilities.ElementActions;

public class HomePage extends BasePage{
	
	public WebDriver driver;
	ElementActions elementActions;
	
	//1. Creating the page objects/locators with By class
	 By homePageHeader = By.xpath("//a[text()='Dashboard']");
	 By loggedinUserName = By.xpath("//span[.='Le']");
	 By logOutBlock = By.xpath("//a[@title='Learn Selenium (learnselenium43@gmail.com)']/parent::button[@type='button']/..");
	 By logOutLink =By.xpath("//div[text()='Logout']");
	 
	 By contactsMenu = By.xpath("//i[@id='menu']");
	 By contactsPageLink = By.xpath("//span[text()='Contacts']");
		 
		 
	//2. Create the Constructor of page class
	 public HomePage(WebDriver driver){
		 this.driver=driver;
		 elementActions = new ElementActions(this.driver);
	 }
		
	 
	//3. Creating Page Actions/Methods for the above page objects 
	public String getHomePageTitle(){
		return elementActions.doGetPageTitle(AppConstants.HOME_PAGE_TITLE);
	}
	
	
	public String isHomePageHeaderExist(){
		elementActions.waitForElementPresent(homePageHeader);
		if(elementActions.doisDisplayed(homePageHeader)){
			return elementActions.doGetText(homePageHeader);
		}
		return null;
	}
	
	public String isUserLoggedIn(){
		elementActions.waitForElementPresent(loggedinUserName);
		if(elementActions.doisDisplayed(loggedinUserName)){
			 return elementActions.doGetText(loggedinUserName);
		}
		return null;
	}
	
	public LoginPage doLogOut(){
		elementActions.waitForElementPresent(logOutBlock);
		if(elementActions.doisDisplayed(logOutBlock)){
			elementActions.doActionsClick(logOutBlock);
			elementActions.waitForElementVisible(logOutLink);
			elementActions.doActionsClick(logOutLink);
		}
		return new LoginPage(driver);
	}
	
	public ContactsPage goToContactsPage(){
		clickOnContacts();
		return new ContactsPage(driver);
	}
	
	public void clickOnContacts(){
		 elementActions.waitForElementPresent(contactsMenu);
		 elementActions.doClick(contactsMenu);
		 
		 elementActions.waitForElementPresent(contactsPageLink);
		 elementActions.doActionsClick(contactsPageLink);
	}
	
	
	
	
}
