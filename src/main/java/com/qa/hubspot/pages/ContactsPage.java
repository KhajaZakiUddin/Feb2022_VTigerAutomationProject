package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pojo.Contacts;
import com.qa.hubspot.utilities.AppConstants;
import com.qa.hubspot.utilities.ElementActions;
import com.qa.hubspot.utilities.JavaScriptUtil;

public class ContactsPage extends BasePage{

	WebDriver driver;
	ElementActions elementActions;
	JavaScriptUtil js;
	
	//1. Creating the page objects/locators with By class
		 
		 //By addContactButton = By.xpath("//button[@title='Add Contact']");
		 By firstName = By.xpath("//input[contains(@name,'firstname')]");
		 By lastName = By.xpath("//input[@name='lastname']");
		 By email = By.xpath("//input[@name='email']");
		 By phoneNumber = By.xpath("//input[@name='phone']");
		 By saveButton = By.xpath("//button[text()='Save']");
		 By quickCreateButton = By.xpath("//span[@title='Quick Create']//i[@class='fas fa-plus-circle font-18']");
		 By createContactLink = By.xpath("//div[text()='Contact']");
		
			 
			 
	//2. Create the Constructor of page class
		 public ContactsPage(WebDriver driver){
			 this.driver=driver;
			 elementActions = new ElementActions(this.driver);
		 }
		 
	//3. Creating the Page Actions
		 public String getContactsPageTitle(){
			 return elementActions.doGetPageTitle(AppConstants.CONTACTS_PAGE_TITLE);
		 }
		 
		 
		 public void createContact(Contacts contacts) throws Exception{			 
			 
			 elementActions.waitForElementPresent(quickCreateButton);
			 elementActions.waitForElementPresent(createContactLink);
			 elementActions.doActionsClick(createContactLink);
			 		 
			 elementActions.waitForElementPresent(this.firstName);
			 elementActions.doSendKeys(this.firstName, contacts.getFirstName());
			 
			 elementActions.waitForElementPresent(this.lastName);
			 elementActions.doSendKeys(this.lastName, contacts.getLastName());
			 
			 elementActions.waitForElementPresent(this.email);
			 elementActions.doSendKeys(this.email, contacts.getEmailId());
			 
			 elementActions.waitForElementPresent(this.phoneNumber);
			 elementActions.doSendKeys(this.phoneNumber, contacts.getPhoneNumber());			 
			 elementActions.doActionsClick(saveButton);
			 
			 Thread.sleep(3000);
	 }
		 
		

}
