package com.framework.testbed;

import org.openqa.selenium.WebDriver;

public class DriverManager{

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver getDriver(){
		return webDriver.get();
	}
	
	public void setDriver(WebDriver driver){
		webDriver.set(driver);
	}
}
