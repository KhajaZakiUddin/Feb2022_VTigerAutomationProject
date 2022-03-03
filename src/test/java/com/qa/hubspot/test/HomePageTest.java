package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pojo.Credentials;
import com.qa.hubspot.utilities.AppConstants;

public class HomePageTest {
	
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
		homePage = loginPage.doLogin(credentials);
		
	}
	
	
	@Test(priority = 3)
	public void verifyHomePageTitleTest(){
		String title = homePage.getHomePageTitle();
		System.out.println("HomePage title is : " +title);
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test(priority = 1)
	public void verifyHomePageHeader(){
		String homePageHeader = homePage.isHomePageHeaderExist();
		System.out.println("HomePage header is : " +homePageHeader);
		Assert.assertEquals(homePageHeader, AppConstants.HOME_PAGE_HEADER);
	}
	
	@Test(priority = 2)
	public void verifyLoggedInUserTest(){
		String loggedInUserName = homePage.isUserLoggedIn();
		System.out.println("Logged-in user is : " +loggedInUserName);
		Assert.assertEquals(loggedInUserName, prop.getProperty("accountName"));
		
	}
	
	@Test(priority = 4)
	public void logOutofApp(){
		loginPage = homePage.doLogOut();
		Assert.assertTrue(loginPage.verifyLogoHeader());
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}
