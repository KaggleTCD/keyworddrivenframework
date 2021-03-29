package com.qa.basepackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	
	public WebDriver webDriver;
	public Properties prop;
	
	public WebDriver init_driver(String browserName) {
		
		if(browserName.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", "C://chromedriver//chromedriver.exe");
			webDriver = new ChromeDriver();
			if(prop.getProperty("headless").equals("yes")) {
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				webDriver = new ChromeDriver(options);
			}
			
			else {
				
				webDriver = new ChromeDriver();
			}
		}
		
		return webDriver;
	}
	
	public Properties init_properties() {
		
		prop = new Properties();
		
		try {
			FileInputStream fileInputStream = new FileInputStream("C:\\Selenium\\keyworddrivenframework\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fileInputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

}
