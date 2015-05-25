package com.framework.ui;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.framework.testbed.DriverWrapper;


public class UIElements <T extends UIElement>{

	protected String uiLocator; 
	public UIElements(String locator){
		uiLocator = locator;
	}
	
	public String getLocator(){
		return uiLocator;
	}
	
	public List<WebElement> getElementsList(WebDriver driver, String locator) {
		return new DriverWrapper().getWebElements(driver, this);
	}
}
