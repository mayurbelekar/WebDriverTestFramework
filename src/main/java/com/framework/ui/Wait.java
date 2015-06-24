package com.framework.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.framework.testbed.DriverWrapper;

public class Wait extends UIElement{

	WebDriverWait wait = null;
	WebElement element = null;
	
	public Wait(String locator){
		super(locator);
	}
	
	public void elementToBeClickable(WebDriver driver){
		new DriverWrapper().elementToBeClickable(driver, this);
	}
	
	public void invisiblityOfTheElement(){
		new DriverWrapper().invisiblityOfTheElement(this);
	}
}
