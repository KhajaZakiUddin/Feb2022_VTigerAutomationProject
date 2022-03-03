package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pojo.Contacts;
import com.qa.hubspot.pojo.Credentials;
import com.qa.hubspot.utilities.AppConstants;
import com.qa.hubspot.utilities.ExcelUtil;

public class ContactsPageTest extends BasePage{
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	Credentials credentials;
	Contacts contacts;
	
	@BeforeTest
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		credentials = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(credentials);
		contactsPage = homePage.goToContactsPage();
		
	}
	
	
	@Test(priority = 1)
	public void verifyContactsPageTitleTest(){
		String title = contactsPage.getContactsPageTitle();
		System.out.println("ContactsPage Title is : " +title);
		Assert.assertEquals(title, AppConstants.CONTACTS_PAGE_TITLE);
	}
	
	
	
	@DataProvider
	public Object[][] getContactsData(){
		Object data[][] = ExcelUtil.getTestData(AppConstants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	
	
	@Test(priority = 2, dataProvider = "getContactsData")
	public void createNewContactsTests(String firstName, String lastName, String emailId, String phoneNumber) throws Exception{
		contacts = new Contacts(firstName, lastName, emailId,phoneNumber);
		contactsPage.createContact(contacts);
//		homePage.goToContactsPage();
	}
	
	@Test(priority = 3)
	public void logOutofApp(){
		loginPage = homePage.doLogOut();
		Assert.assertTrue(loginPage.verifyLogoHeader());
	}
	
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
}
