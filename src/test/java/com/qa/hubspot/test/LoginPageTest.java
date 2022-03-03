package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pojo.Credentials;
import com.qa.hubspot.utilities.AppConstants;

public class LoginPageTest extends BasePage{
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials credentials;
	
	@BeforeTest
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		credentials = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 2)
	public void verifyLoginPageTitleTest(){
		String loginPageTitle = loginPage.getPageTitle();
		System.out.println("Log in Page Title is : " +loginPageTitle);
		Assert.assertEquals(loginPageTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 1)
	public void verifyLoginPageHeaderTest(){	
		Assert.assertTrue(loginPage.verifyLogoHeader());
		
	}
	

	@Test(priority = 3)
	public void loginTest(){
		homePage = loginPage.doLogin(credentials);
		String loggedInUserName = homePage.isUserLoggedIn();
		System.out.println("Logged-in user is : " +loggedInUserName);
		Assert.assertEquals(loggedInUserName, prop.getProperty("accountName"));
	}
	
	
	@DataProvider
	public Object[][] getLoginInvalidData(){
		Object data[][] ={
				{"test@gmail.com", "test"},
				{"test@gmail.com", " "},
				{" ", "test"},
				{" ", " "},
		};
		return data;
	}
	
//	@Test(priority = 4, dataProvider = "getLoginInvalidData", enabled=false)
//	public void login_InvalidTest(String emailId, String pwd){
//		loginPage.doLogin(emailId, pwd);
//		Assert.assertTrue(loginPage.checkLoginErrorMessage());
//	}
	
	@Test(priority = 5)
	public void logOutofApp(){
		loginPage = homePage.doLogOut();
		Assert.assertTrue(loginPage.verifyLogoHeader());
	}
	
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	
	
	

}
