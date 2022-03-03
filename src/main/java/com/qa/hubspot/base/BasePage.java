package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	Properties prop;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver(){
		return tlDriver.get();
	}
	
	
	/**
	 * This Method is used to Initialize the WebDriver on the basis of browserName
	 * @param browserName
	 * @return
	 */
	
	public WebDriver init_driver(Properties prop){
		String browserName = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);
		
		
		if(browserName.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();			
		    tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));	
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));	
			
		}
		else if(browserName.equalsIgnoreCase("safari")){
			WebDriverManager.getInstance(SafariDriver.class).setup();
			tlDriver.set(new SafariDriver());
		}
		else{
			System.out.println(browserName + " is not found. Please pass the right browser name.");
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		return getDriver();
	}
	
	
	/**
	 * 2. This Method returns the Properties prop reference available in config.properties file
	 * @return
	 */
	public Properties init_prop(){
		
		prop=new Properties();
		String path = null;
		String env = null;
		try {
			env = System.getProperty("env");
			if(env == null)
			{
				path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
			}
			else{		
			
			switch(env){
				case "qa":
					path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.qa.properties";
					break;
				case "stg":
					path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.stg.properties";
					break;
				case "prod":
					path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
					break;
				default:
					System.out.println("No Environment is Passed");
					break;
				}	
			}	
			
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Config file is not found at the specified location");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("config file loading is failed");
		}
		
		return prop;
		
	}
	
	
	public String getScreenshot(){
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") +"/Screenshots/"+ System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
	
	
	
	
}
