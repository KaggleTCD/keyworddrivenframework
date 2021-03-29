package com.qa.executionengine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.basepackage.Base;

public class KeyWordEngine {
	
	public WebDriver webDriver;
	public Properties properties;
	public static Workbook book;
	public static Sheet sheet;
	public Base base;
	public WebElement element;
	
	public final String SCENARIO_SHEET = "C:\\Selenium\\keyworddrivenframework\\src\\main\\java\\com\\qa\\hubspot\\keyword\\scenarios\\hubspot.xlsx";
	
	public void startExecution(String sheetName) {
		
		String locatorName = null;
		String locatorValue = null;
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(SCENARIO_SHEET);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		int k=0;
		for(int i=0;i<sheet.getLastRowNum();i++) {
			
		try {
			String locatorColumnValues = sheet.getRow(i+1).getCell(k+1).toString().trim();
			if(!locatorColumnValues.equalsIgnoreCase("NA")) {
				
				locatorName = locatorColumnValues.split("=")[0].trim();
				locatorValue = locatorColumnValues.split("=")[1].trim();
			}
			
			String action = sheet.getRow(i+1).getCell(k+2).toString().trim();
			String value = sheet.getRow(i+1).getCell(k+3).toString().trim();
			
			switch(action) {
			
			case "open browser":
				base=new Base();
				properties = base.init_properties();
				if(value.isEmpty() || value.equals("NA")) {
					
					webDriver = base.init_driver(properties.getProperty("browser"));
				}
				else {
					webDriver = base.init_driver(value);
				}
				break;
			case "enter url":
				if(value.isEmpty() || value.equals("NA")) {
					
					webDriver.get(properties.getProperty("url"));
				}
				else {
					webDriver.get(value);
				}
				break;
			case "quit":
				webDriver.quit();
				break;
				
			default:
				break;
			}
			
			
			switch(locatorName) {
			
			case "id":
				element = webDriver.findElement(By.id(locatorValue));
				if(action.equalsIgnoreCase("sendkeys")) {
					
					element.sendKeys(value);
				}
				else if(action.equalsIgnoreCase("click")) {
					
					element.click();
				}
				locatorName = null;
				break;
			case "linkText":
				element = webDriver.findElement(By.linkText(locatorValue));
				element.click();
				locatorName = null;
				
			default:
				break;
			}
		}
		catch(Exception e) {
			
		}
			
		}
	}
}
